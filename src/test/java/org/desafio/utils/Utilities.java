package org.desafio.utils;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import io.qameta.allure.Attachment;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
public class Utilities {
    private static final String TEMP_DIR = "temp_screenshots/";
    private static class APIResponseInfo {
        String stepName;
        int statusCode;
        String responseBody;

        APIResponseInfo(String stepName, int statusCode, String responseBody) {
            this.stepName = stepName;
            this.statusCode = statusCode;
            this.responseBody = responseBody;
        }
    }
    private static class TestData {
        List<ScreenshotInfo> screenshots;
        List<APIResponseInfo> apiResponses;
        String scenarioName;
        String scenarioTag;
        String status;
        TestData() {
            screenshots = new ArrayList<>();
            apiResponses = new ArrayList<>();
            log.info("New TestData instance created");
        }
    }
    private static TestData currentTest = new TestData();
    private class ScreenshotInfo {
        String stepName;
        String tempPath;
        ScreenshotInfo(String stepName, String tempPath) {
            this.stepName = stepName;
            this.tempPath = tempPath;
        }
    }
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
public void takeScreenshot(WebDriver driver, String fileName, Document documentEvidence) throws IOException, InterruptedException {
    Thread.sleep(1000);
    if (currentTest == null || currentTest.screenshots == null) {
        log.error("TestData not properly initialized");
        currentTest = new TestData();
    }
    try {
        File tempDir = new File(TEMP_DIR);
        if (!tempDir.exists()) {
            boolean created = tempDir.mkdirs();
            log.info("Temp directory created: " + created);
        }
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String tempPath = TEMP_DIR + UUID.randomUUID() + "_" + fileName;
        File tempFile = new File(tempPath);
        FileUtils.copyFile(screenshot, tempFile);
        Thumbnails.of(tempFile).scale(0.5).toFile(tempFile);
        String stepName = fileName.split("\\.")[0];
        currentTest.screenshots.add(new ScreenshotInfo(stepName, tempPath));
        log.info("Screenshot added: " + tempPath);
        log.info("Current screenshots count: " + currentTest.screenshots.size());
    } catch (Exception e) {
        log.error("Error in takeScreenshot", e);
        throw e;
    }
}
    public Document createDocumentPDF(String scenarioName, String scenarioTag) throws IOException {
        currentTest.scenarioName = scenarioName;
        currentTest.scenarioTag = scenarioTag;
        int year = LocalDateTime.now().getYear();
        int month = LocalDateTime.now().getMonthValue();
        int day = LocalDateTime.now().getDayOfMonth();
        // Create evidencias directory if it doesn't exist
        Path folder = Paths.get("evidencias",String.valueOf(year),String.format("%02d", month), String.format("%02d", day));
        new File(folder.toString()).mkdirs();
        // Get current timestamp
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss"));
        // Create the actual document file path
        String documentName = folder +"/" + scenarioTag + "_" +
                scenarioName.replaceAll("[^a-zA-Z0-9]", "_") + "_TestEvidence"+"_"+timestamp+".pdf";
        // Create and return the actual document
        return new Document(new PdfDocument(new PdfWriter(documentName)));
    }
    public void addHeaderToDocument(Document document, String scenarioName, String status) {
        try {
            document.add(new Paragraph("\n"))
                    .add(new Paragraph("Test Execution Evidence")
                            .setUnderline().simulateBold()
                            .setFontSize(16)
                            .setTextAlignment(TextAlignment.CENTER))
                    .add(new Paragraph("\nScenario: " + scenarioName)
                            .setFontSize(10))
                    .add(new Paragraph("Status: " + status)
                            .setFontSize(10)
                            .setFontColor(status.equals("PASSED") ? ColorConstants.GREEN : ColorConstants.RED)
                            .setDestination("status"))
                    .add(new Paragraph("Executed by: " + System.getProperty("user.name"))
                            .setFontSize(10))
                    .add(new Paragraph("Execution Time: " +
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                            .setFontSize(10))
                    .add(new Paragraph("\n"));
        } catch (Exception e) {
            log.error("Error adding header to document", e);
        }
    }
    public void generateDocumentPDF(Document document, String scenarioName, String status) {
        try {
            currentTest.status = status;
            // Add header to existing document
            addHeaderToDocument(document, currentTest.scenarioName, currentTest.status);
            // Add API responses
            if (!currentTest.apiResponses.isEmpty()) {
                document.add(new Paragraph("\nAPI Responses:").setUnderline().simulateBold());
                for (APIResponseInfo apiResponse : currentTest.apiResponses) {
                    document.add(new Paragraph("Step: " + apiResponse.stepName));
                    document.add(new Paragraph("Status Code: " + apiResponse.statusCode));
                    document.add(new Paragraph("Response Body: " + apiResponse.responseBody));
                    document.add(new Paragraph("\n"));
                }
            }
            // Add screenshots to existing document
            for (ScreenshotInfo screenshot : currentTest.screenshots) {
                try {
                    File imageFile = new File(screenshot.tempPath);
                    if (imageFile.exists()) {
                        document.add(new Paragraph("Step: " + screenshot.stepName));
                        Image image = new Image(ImageDataFactory.create(screenshot.tempPath));
                        document.add(image);
                        document.add(new Paragraph("\n"));
                    }
                } catch (Exception e) {
                    log.error("Error adding screenshot: " + screenshot.tempPath, e);
                }
            }
            // Close the document
            document.close();
            log.info("Document generated successfully");
            // Cleanup
            cleanup();
        } catch (Exception e) {
            log.error("Error generating document", e);
        }
    }
    private void cleanup() {
        try {
            // Delete all temporary files
            for (ScreenshotInfo screenshot : currentTest.screenshots) {
                new File(screenshot.tempPath).delete();
            }
            // Delete temp directory
            FileUtils.deleteDirectory(new File(TEMP_DIR));
            // Reset test data
            currentTest = new TestData();
        } catch (Exception e) {
            log.error("Error during cleanup", e);
        }
    }
    public void addResponseToDocument(Response response) {
        try {
            String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
            int statusCode = response.getStatusCode();
            String responseBody = response.getBody().asString();
            currentTest.apiResponses.add(new APIResponseInfo(stepName, statusCode, responseBody));
            log.info("API response added: " + stepName);
        } catch (Exception e) {
            log.error("Error adding API response", e);
        }
    }
}


//package org.desafio.utils;
//import com.itextpdf.io.image.ImageData;
//import com.itextpdf.io.image.ImageDataFactory;
//import com.itextpdf.kernel.colors.ColorConstants;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.element.Image;
//import com.itextpdf.layout.element.Paragraph;
//import com.itextpdf.layout.properties.TextAlignment;
//import io.qameta.allure.Attachment;
//import io.restassured.response.Response;
//import lombok.extern.log4j.Log4j2;
//import net.coobird.thumbnailator.Thumbnails;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import java.io.File;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//
//@Log4j2
//public class UtilitiesBackup {
//    private List<String> screenshots = new ArrayList<>();
//    @Attachment(value = "Screenshot", type = "image/png")
//    public byte[] takeScreenshot(WebDriver driver) {
//        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//    }
//    public void takeScreenshot(WebDriver driver, String fileName, Document documentEvidence) throws InterruptedException {
//        // Adiciona um atraso de 1 segundos
//        Thread.sleep(1000);
//        takeScreenshot(driver);
//        // Captura o screenshot
//        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        //adiciono o screenshot no pdf
//        try {
//            String filePath = "evidencias/" + fileName;
//            FileUtils.copyFile(screenshot, new File(filePath));
//            Thumbnails.of(screenshot).scale(0.5).toFile(new File(filePath));
//            String stepName = fileName.split("\\.")[0];
//            addScreenshotToDocument(documentEvidence, filePath, stepName);
//            deleteScreenshot(filePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public Document createDocumentPDF(String scenarioName, String scenarioTag) throws IOException {
//        String documentName = "evidencias/" + scenarioTag + "_" +
//                scenarioName.replaceAll("[^a-zA-Z0-9]", "_") + "_TestEvidence.pdf";
//        PdfWriter writer = new PdfWriter(documentName);
//        PdfDocument pdf = new PdfDocument(writer);
//        Document document = new Document(pdf);
//        addHeaderToDocument(document, scenarioName, "IN PROGRESS");
//       return document;
//    }
//
//    public void addHeaderToDocument(Document document, String scenarioName, String status) {
//        try {
//            Paragraph headerBlock = new Paragraph()
//                    .add(new Paragraph("\n"))
//                    .add(new Paragraph("Test Execution Evidence")
//                            .setUnderline().simulateBold()
//                            .setFontSize(16)
//                            .setTextAlignment(TextAlignment.CENTER))
//                    .add(new Paragraph("\nScenario: " + scenarioName)
//                            .setFontSize(12))
//                    .add(new Paragraph("Status: " + status)
//                            .setFontSize(12)
//                            .setFontColor(status.equals("PASSED") ? ColorConstants.GREEN : ColorConstants.RED)
//                            .setDestination("status"))
//                    .add(new Paragraph("Executed by: " + System.getProperty("user.name"))
//                            .setFontSize(12))
//                    .add(new Paragraph("Execution Time: " +
//                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
//                            .setFontSize(12))
//                    .add(new Paragraph("\n"));
//        } catch (Exception e) {
//            log.error("Error adding header to document", e);
//        }
//    }
//
//    public void addScreenshotToDocument(Document document, String screenshot, String stepName) {
//        try {
//            document.add(new Paragraph("Step: " + stepName));
//            ImageData imageData = ImageDataFactory.create(screenshot);
//            Image image = new Image(imageData);
//            document.add(image);
//            document.add(new Paragraph("\n"));
//            log.info("Screenshot added to document: " + screenshot);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public void deleteScreenshot(String screenshot) {
//        File file = new File(screenshot);
//        if (file.delete()) {
//            log.info("Screenshot deleted: " + screenshot);
//        } else {
//            log.warn("Failed to delete screenshot: " + screenshot);
//        }
//    }
//    public void generateDocumentPDF(Document document, String scenarioName, String status) throws IOException {
//        addHeaderToDocument(document, scenarioName, status);
//        document.close();
//        log.info("Document generated: " + scenarioName);
//    }
//    public void addResponseToDocument(Document document, Response response) {
//        int statusCode = response.getStatusCode();
//        String responseBody = response.getBody().asString();
//        document.add(new Paragraph("Status code: " + statusCode));
//        document.add(new Paragraph("Response: " + responseBody));
//    }
//}
//

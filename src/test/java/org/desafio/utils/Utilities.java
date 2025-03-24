package org.desafio.logic.utils;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Log4j2
public class Utilities {
    private List<String> screenshots = new ArrayList<>();
    private static List<WebDriver> drivers = new ArrayList<>();
    public static void addDriver(WebDriver driver) {
        drivers.add(driver);
    }
    public static void quitAllDrivers() {
        for (WebDriver driver : drivers) {
            if (driver != null) {
                driver.quit();
            }
        }
        drivers.clear();
    }
    public void runPowerShellCommand(String command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("powershell.exe", command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void takeScreenshot(WebDriver driver, String fileName, Document documentEvidence) throws InterruptedException {
        // Adiciona um atraso de 1 segundos
        Thread.sleep(1000);
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //adiciono o screenshot no pdf
        try {
            String filePath = "evidencias/" + fileName;
            FileUtils.copyFile(screenshot, new File(filePath));
            Thumbnails.of(screenshot).scale(0.5).toFile(new File(filePath));
            String stepName = fileName.split("\\.")[0];
            addScreenshotToDocument(documentEvidence, filePath, stepName);
            deleteScreenshot(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Document createDocumentPDF(String scenarioName) throws IOException {
        String documentName = "evidencias/" +
                scenarioName.replaceAll("[^a-zA-Z0-9]", "_") + "_TestEvidence.pdf";
        PdfWriter writer = new PdfWriter(documentName);
        PdfDocument pdf = new PdfDocument(writer);
       return new Document(pdf);
    }

    public void addScreenshotToDocument(Document document, String screenshot, String stepName) {
        try {
            document.add(new Paragraph("Step: " + stepName));
            ImageData imageData = ImageDataFactory.create(screenshot);
            Image image = new Image(imageData);
            document.add(image);
            document.add(new Paragraph("\n"));
            log.info("Screenshot added to document: " + screenshot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteScreenshot(String screenshot) {
        File file = new File(screenshot);
        if (file.delete()) {
            log.info("Screenshot deleted: " + screenshot);
        } else {
            log.warn("Failed to delete screenshot: " + screenshot);
        }
    }
    public void generateDocumentPDF(Document document, String scenarioName) {
        document.close();
        log.info("Document generated: " + scenarioName);
    }

}


package org.desafio.config;

import com.itextpdf.layout.Document;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.Getter;
import lombok.Setter;
import org.desafio.utils.DocumentConfig;
import io.restassured.response.Response;
import java.io.IOException;

public class CucumberHooks {

    @Getter
    private static Document documentEvidence;
    private static String scenarioName;
    private static String scenarioTag;
    private static String scenarioTags;
    private static final DocumentConfig DOCUMENT_CONFIG = new DocumentConfig();

    @Setter
    private static Response response;

    @Before(order = 1)
    public void setUp(Scenario scenario) throws IOException {
        scenarioTags = scenario.getSourceTagNames().toString();
        if (!scenarioTags.contains("API")) {
            DriverManager.getDriver();
        }
        scenarioName = scenario.getName();
        scenarioTag = scenario.getSourceTagNames().iterator().next().replace("@", "");
        documentEvidence = DOCUMENT_CONFIG.createDocumentPDF(scenarioName, scenarioTag);
    }
    @After(order = 1)
    public void tearDown(Scenario scenario) throws IOException {
        String status = scenario.isFailed() ? "FAILED" : "PASSED";
        DOCUMENT_CONFIG.generateDocumentPDF(documentEvidence, status);
        if (!scenarioTags.contains("API")){
            DriverManager.quitDriver();
        }

    }

}
package org.desafio.logic;

import com.itextpdf.layout.Document;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.desafio.logic.pages.HomePage;
import org.desafio.logic.utils.Utilities;
import org.openqa.selenium.edge.EdgeDriver;

@Log4j2
public class HomeLogic {
    private String step = "";
    private WebDriver driver;
    private Utilities utilities;
    private HomePage homePage;

    public HomeLogic() {
        utilities = new Utilities();
        driver = new EdgeDriver();
        Utilities.addDriver(driver);
        homePage = new HomePage(driver);
    }

//    @Step("Validate Title Products")
//    public void validateTitleProducts(Document documentEvidence) throws InterruptedException {
//        step = "Validate title";
//        log.info(step);
//        homePage.validateLogin();
//        utilities.takeScreenshot(driver, step + ".png", documentEvidence);
//    }
}

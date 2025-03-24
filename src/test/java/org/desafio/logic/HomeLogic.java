package org.desafio.logic;

import com.itextpdf.layout.Document;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.desafio.pages.HomePage;
import org.desafio.logic.utils.Utilities;
import org.openqa.selenium.edge.EdgeDriver;

@Log4j2
public class HomeLogic {
    private WebDriver driver;
    private String step = "";
    private Utilities utilities;
    private HomePage homePage;

    public HomeLogic() {
        utilities = new Utilities();
        homePage = new HomePage(driver);
    }

    @Step("Add the Sauce Bike Light to cart")
    public void addSauceBikeLightToCart(Document documentEvidence) throws InterruptedException {
        step = "Add the Sauce Bike Light to cart";
        log.info(step);
        homePage.addSauceBikeLightToCart();
        homePage.validateCart();
        utilities.takeScreenshot(driver, step + ".png", documentEvidence);
    }

}

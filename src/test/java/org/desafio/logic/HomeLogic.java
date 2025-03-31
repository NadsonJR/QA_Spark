package org.desafio.logic;

import com.itextpdf.layout.Document;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.desafio.config.DriverManager;
import org.openqa.selenium.WebDriver;
import org.desafio.pages.HomePage;
import org.desafio.utils.Utilities;

import java.io.IOException;


@Log4j2
public class HomeLogic {
    private WebDriver driver;
    private String step = "";
    private Utilities utilities;
    private HomePage homePage;

    public HomeLogic() {
        utilities = new Utilities();
        driver = DriverManager.getDriver(); // Initialize the driver
        homePage = new HomePage(driver); // Pass the driver to HomePage
    }
    @Step("Add the Sauce Bike Light to cart")
    public void addSauceBikeLightToCart(Document documentEvidence) throws InterruptedException, IOException {
        step = "Add the Sauce Bike Light to cart";
        log.info(step);
        homePage.addSauceBikeLightToCart();
        homePage.validateCart();
        utilities.takeScreenshot(driver, step + ".png", documentEvidence);
    }
    @Step("Open the cart")
    public void openCart(Document documentEvidence) throws InterruptedException, IOException {
        step = "Open the cart";
        log.info(step);
        homePage.openCart();
        utilities.takeScreenshot(driver, step + ".png", documentEvidence);
    }
    @Step("Verify the cart")
    public void verifyCart(Document documentEvidence) throws InterruptedException, IOException {
        step = "Verify the cart";
        log.info(step);
        homePage.verifyCart();
        utilities.takeScreenshot(driver, step + ".png", documentEvidence);
    }
    @Step("Click on Checkout button")
    public void clickCheckoutButton(Document documentEvidence) throws InterruptedException, IOException {
        step = "Click on Checkout button";
        log.info(step);
        homePage.clickCheckoutButton();
        utilities.takeScreenshot(driver, step + ".png", documentEvidence);
    }
    @Step("Fill zip form")
    public void fillZipForm(Document documentEvidence) throws InterruptedException, IOException {
        step = "Fill zip form";
        log.info(step);
        homePage.fillZipForm();
        utilities.takeScreenshot(driver, step + ".png", documentEvidence);
    }
    @Step("Click on continue button")
    public void clickContinueButton(Document documentEvidence) throws InterruptedException, IOException {
        step = "Click on continue button";
        log.info(step);
        homePage.clickContinueButton();
        utilities.takeScreenshot(driver, step + ".png", documentEvidence);
    }
    @Step("Validate the overview")
    public void validateOverview(Document documentEvidence) throws InterruptedException, IOException {
        step = "Validate the overview";
        log.info(step);
        homePage.validateOverview();
        utilities.takeScreenshot(driver, step + ".png", documentEvidence);
    }
    @Step("Click on finish button")
    public void clickFinishButton(Document documentEvidence) throws InterruptedException, IOException {
        step = "Click on finish button";
        log.info(step);
        homePage.clickFinishButton();
        utilities.takeScreenshot(driver, step + ".png", documentEvidence);
    }

    @Step("Validate the complete checkout")
    public void validateCompleteCheckout(Document documentEvidence) throws InterruptedException, IOException {
        step = "Validate the complete checkout";
        log.info(step);
        homePage.validateCompleteCheckout();
        utilities.takeScreenshot(driver, step + ".png", documentEvidence);
    }

}

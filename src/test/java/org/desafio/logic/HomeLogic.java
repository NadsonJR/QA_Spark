package org.desafio.logic;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.desafio.config.DriverManager;
import org.openqa.selenium.WebDriver;
import org.desafio.pages.HomePage;
import org.desafio.utils.DocumentConfig;

import java.io.IOException;


@Log4j2
public class HomeLogic {
    private WebDriver driver;
    private String step = "";
    private DocumentConfig documentConfig;
    private HomePage homePage;

    public HomeLogic() {
        documentConfig = new DocumentConfig();
        driver = DriverManager.getDriver(); // Initialize the driver
        homePage = new HomePage(driver); // Pass the driver to HomePage
    }
    @Step("Add the Sauce Bike Light to cart")
    public void addSauceBikeLightToCart() throws InterruptedException, IOException {
        step = "Add the Sauce Bike Light to cart";
        log.info(step);
        homePage.addSauceBikeLightToCart(step);
        step = "Validate the remove button";
        log.info(step);
        homePage.validateRemoveBtn(step);
        step = "Validate the cart";
        log.info(step);
        homePage.validateCart(step);
    }
    @Step("Open the cart")
    public void openCart() throws InterruptedException, IOException {
        step = "Open the cart";
        log.info(step);
        homePage.openCart(step);
    }
    @Step("Verify the cart")
    public void verifyCart() throws InterruptedException, IOException {
        step = "Verify the cart product";
        log.info(step);
        homePage.verifyCartElement(step);
        step= "Verify the cart description";
        log.info(step);
        homePage.verifyItemDescription(step);
        step = "Verify the cart price";
        log.info(step);
        homePage.verifyItemCartPriceElement(step);

    }
    @Step("Click on Checkout button")
    public void clickCheckoutButton() throws InterruptedException, IOException {
        step = "Click on Checkout button";
        log.info(step);
        homePage.clickCheckoutButton(step);
    }
    @Step("Fill zip form")
    public void fillZipForm() throws InterruptedException, IOException {
        step = "Fill zip form";
        log.info(step);
        homePage.fillZipForm(step);
    }
    @Step("Click on continue button")
    public void clickContinueButton() throws InterruptedException, IOException {
        step = "Click on continue button";
        log.info(step);
        homePage.clickContinueButton(step);
    }
    @Step("Validate the overview")
    public void validateOverview() throws InterruptedException, IOException {
        step = "Validate Cart element is present";
        log.info(step);
        homePage.verifyCartElement(step);
        step = "Validate Item Description is present";
        log.info(step);
        homePage.verifyItemDescription(step);
        step = "Validate Prices is present";
        log.info(step);
        homePage.validatePrices();

    }
    @Step("Click on finish button")
    public void clickFinishButton() throws InterruptedException, IOException {
        step = "Click on finish button";
        log.info(step);
        homePage.clickFinishButton(step);
    }

    @Step("Validate the complete checkout")
    public void validateCompleteCheckout() throws InterruptedException, IOException {
        step = "Validate the complete checkout";
        log.info(step);
        homePage.validateCompleteCheckout();
    }

}

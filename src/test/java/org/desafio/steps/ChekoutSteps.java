package org.desafio.steps;

import com.itextpdf.layout.Document;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.desafio.config.BaseConfig;
import org.desafio.config.CucumberHooks;
import org.desafio.logic.HomeLogic;
import org.desafio.logic.LoginLogic;

import java.io.IOException;

@Log4j2
public class ChekoutSteps {

    private Document documentEvidence;
    private HomeLogic homeLogic;
    private LoginLogic loginLogic;

    public ChekoutSteps()  {
        homeLogic = new HomeLogic();
        loginLogic = new LoginLogic();
        documentEvidence = CucumberHooks.getDocumentEvidence();
    }

    @Given("I open the Swag Labs")
    public void i_open_the_swag_labs() {
        loginLogic.navigateTo(BaseConfig.BASE_URL);
    }
    @Given("Login on Swag Labs")
    public void login_on_swag_labs() throws InterruptedException , IOException{
        loginLogic.enterUsername("standard_user");
        loginLogic.enterPassword("secret_sauce");
        loginLogic.clickLoginButton();
        loginLogic.validateTitleProducts();
    }
    @Given("Add the Sauce Bike Light to cart")
    public void add_the_sauce_bike_light_to_cart() throws InterruptedException, IOException {
        homeLogic.addSauceBikeLightToCart();
    }
    @Given("Checkout the product")
    public void checkout_the_product() throws InterruptedException, IOException {
        homeLogic.openCart();
        homeLogic.verifyCart();
        homeLogic.clickCheckoutButton();
        homeLogic.fillZipForm();
        homeLogic.clickContinueButton();
    }

    @Then("Confirm the order")
    public void confirm_the_order() throws InterruptedException, IOException {
        homeLogic.validateOverview();
        homeLogic.clickFinishButton();
        homeLogic.validateCompleteCheckout();
    }


}

package org.desafio.steps;

import com.itextpdf.layout.Document;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import lombok.extern.log4j.Log4j2;
import org.desafio.logic.HomeLogic;
import org.desafio.logic.LoginLogic;
import org.desafio.logic.utils.Utilities;
import org.desafio.utils.DriverManager;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

@Log4j2
public class ChekoutSteps {
    private Document documentEvidence;
    private HomeLogic homeLogic;
    private LoginLogic loginLogic;
    private Utilities utilities;
    private String scenarioName;
    @Before
    public void setUp(Scenario scenario) throws IOException {
        utilities = new Utilities();
        homeLogic = new HomeLogic();
        loginLogic = new LoginLogic();
        scenarioName = scenario.getName();
        documentEvidence = utilities.createDocumentPDF(scenarioName);
    }
    @Given("I open the Swag Labs")
    public void i_open_the_swag_labs() {
        loginLogic.navigateTo("https://www.saucedemo.com");
    }
    @Given("Login on Swag Labs")
    public void login_on_swag_labs() throws InterruptedException {
        loginLogic.enterUsername("standard_user", documentEvidence);
        loginLogic.enterPassword("secret_sauce", documentEvidence);
        loginLogic.clickLoginButton(documentEvidence);
        loginLogic.validateTitleProducts(documentEvidence);
    }
    @Given("Add the Sauce Bike Light to cart")
    public void add_the_sauce_bike_light_to_cart() throws InterruptedException {
        homeLogic.addSauceBikeLightToCart(documentEvidence);
    }

}

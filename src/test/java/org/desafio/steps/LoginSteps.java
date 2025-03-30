package org.desafio.steps;

import com.itextpdf.layout.Document;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.desafio.config.CucumberHooks;
import org.desafio.logic.HomeLogic;
import org.desafio.logic.LoginLogic;
import org.desafio.utils.Utilities;
import org.desafio.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;
@Log4j2
public class LoginSteps {

    private LoginLogic loginLogic;
    private HomeLogic homeLogic;
    private DriverManager driverManager;
    private Utilities utilities;
    private String scenarioName;
    private Document documentEvidence;

    public LoginSteps() {
        loginLogic = new LoginLogic();
        homeLogic = new HomeLogic();
        documentEvidence = CucumberHooks.getDocumentEvidence();
    }

    @Given("I open the login page")
    public void i_open_the_login_page() throws InterruptedException {
        loginLogic.navigateTo("https://www.saucedemo.com");
    }
    @Then("I should see the title {string}")
    public void i_should_see_the_title(String expectedTitle) throws InterruptedException, IOException {
        loginLogic.validateTitle(expectedTitle, documentEvidence);
    }
    @Then("I should see the text {string}")
    public void i_should_see_the_text(String string) throws InterruptedException, IOException {
        loginLogic.validateText(string, documentEvidence);
    }
    @Given("fill e-mail input {string}")
    public void fill_e_mail_input(String email) throws InterruptedException, IOException {
        loginLogic.enterEmail(email, documentEvidence);
    }
    @Given("fill username input {string}")
    public void fill_username_input(String username) throws InterruptedException, IOException {
        loginLogic.enterUsername(username, documentEvidence);
    }
    @Given("fill password input {string}")
    public void fill_password_input(String password) throws InterruptedException, IOException {
        loginLogic.enterPassword(password, documentEvidence);
    }
    @Then("click on btn login")
    public void click_on_btn_login() throws InterruptedException, IOException {
        loginLogic.clickLoginButton(documentEvidence);
    }

    @Then("Validate if user is logged in")
    public void validate_if_user_is_logged_in() throws InterruptedException, IOException {
        loginLogic.validateTitleProducts(documentEvidence);
    }

    @Then("Validate error login message")
    public void validate_error_login_message() throws InterruptedException {
        loginLogic.validateErrorPassword(documentEvidence);
    }
}

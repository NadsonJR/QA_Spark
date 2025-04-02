package org.desafio.steps;

import com.itextpdf.layout.Document;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.desafio.config.BaseConfig;
import org.desafio.config.CucumberHooks;
import org.desafio.logic.LoginLogic;

import java.io.IOException;
@Log4j2
public class LoginSteps {

    private LoginLogic loginLogic;
    private Document documentEvidence;

    public LoginSteps() {
        loginLogic = new LoginLogic();
        documentEvidence = CucumberHooks.getDocumentEvidence();
    }

    @Given("I open the login page")
    public void i_open_the_login_page(){
        loginLogic.navigateTo(BaseConfig.BASE_URL);
    }
    @Then("I should see the title {string}")
    public void i_should_see_the_title(String expectedTitle) throws InterruptedException, IOException {
        loginLogic.validateTitle(expectedTitle);
    }
    @Then("I should see the text {string}")
    public void i_should_see_the_text(String text) throws InterruptedException, IOException {
        loginLogic.validateText(text);
    }
    @Given("fill e-mail input {string}")
    public void fill_e_mail_input(String email) throws InterruptedException, IOException {
        loginLogic.enterEmail(email);
    }
    @Given("fill username input {string}")
    public void fill_username_input(String username) throws InterruptedException, IOException {
        loginLogic.enterUsername(username);
    }
    @Given("fill password input {string}")
    public void fill_password_input(String password) throws InterruptedException, IOException {
        loginLogic.enterPassword(password);
    }
    @Then("click on btn login")
    public void click_on_btn_login() throws InterruptedException, IOException {
        loginLogic.clickLoginButton();
    }
    @Then("Validate if user is logged in")
    public void validate_if_user_is_logged_in() throws InterruptedException, IOException {
        loginLogic.validateTitleProducts();
    }
    @Then("Validate error login message")
    public void validate_error_login_message() throws InterruptedException {
        loginLogic.validateErrorPassword();
    }
}

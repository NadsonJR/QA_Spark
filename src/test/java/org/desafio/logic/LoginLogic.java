package org.desafio.logic;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.desafio.pages.LoginPage;
import org.desafio.utils.DocumentConfig;
import org.desafio.pages.HomePage;
import org.desafio.config.DriverManager;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

@Log4j2
public class LoginLogic {
    private String step = "";
    public WebDriver driver;
    private DocumentConfig documentConfig;
    private LoginPage loginPage;
    private HomePage homePage;

    public LoginLogic() {
        this.driver = DriverManager.getDriver();
        DriverManager.addDriver(driver);
        documentConfig = new DocumentConfig();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @Step("Navigate to URL {url}")
    public void navigateTo(String url) throws IOException, InterruptedException {
        step = "Navigate to URL " + url;
        log.info(step);
        loginPage.navigateTo(url, step);
    }

    @Step("Validate title {expectedTitle}")
    public void validateTitle(String expectedTitle) throws InterruptedException, IOException {
        step = "Validate title";
        log.info(step);
        loginPage.validateTitlePage(expectedTitle,step);
    }
    @Step("Enter email {text}")
    public void enterEmail(String text) throws InterruptedException, IOException {
        step = "Fill input email";
        log.info(step);
        loginPage.enterEmail(text);
        documentConfig.takeScreenshot(driver, step);
    }

    @Step("Enter username {text}")
    public void enterUsername(String username) throws InterruptedException, IOException {
        step = "Fill input username";
        log.info(step);
        loginPage.enterUsername(username,step);
    }
    @Step("Enter password {text}")
    public void enterPassword(String password) throws InterruptedException, IOException {
        step = "Fill input password";
        log.info(step);
        loginPage.enterPassword(password,step);
    }
    @Step("Click login button")
    public void clickLoginButton() throws InterruptedException, IOException {
        step = "Click login button";
        log.info(step);
        loginPage.clickLoginButton(step);
    }

    @Step("Validate Title Products")
    public void validateTitleProducts() throws InterruptedException, IOException {
        step = "Validate title";
        log.info(step);
        homePage.validateLogin(step);
    }

    @Step("Validate Erro password")
    public void validateErrorPassword() throws InterruptedException{
        step = "Validate Erro password";
        log.info(step);
        loginPage.validateErroLoginMsg(step);
    }

    @Step("Validate text {expectedText}")
    public void validateText(String expectedText) throws InterruptedException, IOException {
        step = "Validate text";
        log.info(step);
        loginPage.isTextPresent(expectedText, step);
    }
}
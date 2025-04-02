package org.desafio.logic;
import io.qameta.allure.Step;
import com.itextpdf.layout.Document;
import lombok.extern.log4j.Log4j2;
import org.desafio.pages.LoginPage;
import org.desafio.utils.Utilities;
import org.desafio.pages.HomePage;
import org.desafio.config.DriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

@Log4j2
public class LoginLogic {
    private String step = "";
    public WebDriver driver;
    private Utilities utilities;
    private LoginPage loginPage;
    private HomePage homePage;

    public LoginLogic() {
        this.driver = DriverManager.getDriver();
        DriverManager.addDriver(driver);
        utilities = new Utilities();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @Step("Navigate to URL {url}")
    public void navigateTo(String url){
        step = "Navigate to URL " + url;
        log.info(step);
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Step("Validate title {expectedTitle}")
    public void validateTitle(String expectedTitle) throws InterruptedException, IOException {
        step = "Validate title";
        log.info(step);
        String actualTitle = loginPage.getPageTitle();
        if (actualTitle.equals(expectedTitle)) {
            log.info("Title is correct");
            utilities.takeScreenshot(driver, step + ".png");
        } else {
            log.error("Title is incorrect");
            utilities.takeScreenshot(driver, step + ".png");
            Assert.fail("Title is incorrect");
        }
    }
    @Step("Enter email {text}")
    public void enterEmail(String text) throws InterruptedException, IOException {
        step = "Fill input email";
        log.info(step);
        loginPage.enterEmail(text);
        utilities.takeScreenshot(driver, step + ".png");
    }

    @Step("Enter username {text}")
    public void enterUsername(String username) throws InterruptedException, IOException {
        step = "Fill input username";
        log.info(step);
        utilities.HighlightElementScreenshot(driver, loginPage.enterUsername(username),step + ".png");
    }
    @Step("Enter password {text}")
    public void enterPassword(String password) throws InterruptedException, IOException {
        step = "Fill input password";
        log.info(step);
        utilities.HighlightElementScreenshot(driver,loginPage.enterPassword(password),step + ".png");
    }
    @Step("Click login button")
    public void clickLoginButton() throws InterruptedException, IOException {
        step = "Click login button";
        log.info(step);
        loginPage.clickLoginButton();
        utilities.takeScreenshot(driver, step + ".png");
    }

    @Step("Validate Title Products")
    public void validateTitleProducts() throws InterruptedException, IOException {
        step = "Validate title";
        log.info(step);
        utilities.HighlightElementScreenshot(driver, homePage.validateLogin(), step + ".png");
    }

    @Step("Validate Erro password")
    public void validateErrorPassword() throws InterruptedException{
        step = "Validate Erro password";
        log.info(step);
        utilities.HighlightElementScreenshot(driver, loginPage.validateErroLoginMsg(), step + ".png");
    }

    @Step("Validate text {expectedText}")
    public void validateText(String expectedText) throws InterruptedException, IOException {
        step = "Validate text";
        log.info(step);
        boolean isTextPresent = loginPage.isTextPresent(expectedText);
        if (isTextPresent) {
            log.info("Text is present");
            utilities.takeScreenshot(driver, step + ".png");
        } else {
            log.error("Text is not present");
            utilities.takeScreenshot(driver, step + ".png");
            Assert.fail("Text is not present");
        }
    }
}
package org.desafio.pages;

import lombok.extern.log4j.Log4j2;
import org.desafio.utils.DocumentConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
@Log4j2
public class LoginPage {

    private WebDriver driver;

    // Web elements
    private By emailInput = By.name("email");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");
    private By usernameInput = By.id("user-name");
    private By errorLoginMsg = By.xpath("//div//h3[@data-test=\"error\"]");
    private DocumentConfig documentConfig;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        documentConfig = new DocumentConfig();
    }

    public void navigateTo(String url, String step) throws IOException, InterruptedException {
        driver.get(url);
        driver.manage().window().maximize();
    }

    // Page actions
    public void enterEmail(String email) {
        WebElement emailElement = driver.findElement(emailInput);
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    public void enterUsername(String username, String step) {
        WebElement usernameElement = driver.findElement(usernameInput);
        usernameElement.clear();
        usernameElement.sendKeys(username);
        documentConfig.HighlightElementScreenshot(driver, usernameElement, step);
    }
    public void enterPassword(String password, String step) {
        WebElement passwordElement = driver.findElement(passwordInput);
        passwordElement.clear();
        passwordElement.sendKeys(password);
        documentConfig.HighlightElementScreenshot(driver, passwordElement, step);
    }
    public void clickLoginButton(String step) {
        WebElement loginBtn = driver.findElement(loginButton);
        documentConfig.HighlightElementScreenshot(driver, loginBtn, step);
        loginBtn.click();
    }
    public void validateErroLoginMsg(String step){
        WebElement errorMsg = driver.findElement(errorLoginMsg);
        documentConfig.HighlightElementScreenshot(driver, errorMsg, step);
        Assert.assertEquals(errorMsg.getText(),"Epic sadface: Username and password do not match any user in this service");
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void isTextPresent(String text, String step) throws IOException, InterruptedException {
        if (driver.getPageSource().contains(text)) {
            log.info("Text is present");
            documentConfig.takeScreenshot(driver, step);
        } else {
            log.error("Text is not present");
            documentConfig.takeScreenshot(driver, step);
            Assert.fail("Text is not present");
        }

    }

    public void validateTitlePage(String expectedTitle,String step) throws IOException, InterruptedException {
        String actualTitle = getPageTitle();
        if (actualTitle.equals(expectedTitle)) {
            log.info("Title is correct");
            documentConfig.takeScreenshot(driver, "Title is correct");
        } else {
            log.error("Title is incorrect");
            documentConfig.takeScreenshot(driver, "Title is incorrect");
            Assert.fail("Title is incorrect");
        }
    }
}

package org.desafio.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    // Web elements
    private By emailInput = By.name("email");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");
    private By usernameInput = By.id("user-name");
    private By errorLoginMsg = By.xpath("//div//h3[@data-test=\"error\"]");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Page actions
    public void enterEmail(String email) {
        WebElement emailElement = driver.findElement(emailInput);
        emailElement.clear();
        emailElement.sendKeys(email);
    }
    public void enterUsername(String username) {
        WebElement usernameElement = driver.findElement(usernameInput);
        usernameElement.clear();
        usernameElement.sendKeys(username);
    }
    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordInput);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }
    public void clickLoginButton() {
        WebElement loginBtn = driver.findElement(loginButton);
        loginBtn.click();
    }

    public void validateErroLoginMsg(){
        WebElement errorMsg = driver.findElement(errorLoginMsg);
        Assert.assertEquals(errorMsg.getText(),"Epic sadface: Username and password do not match any user in this service");
    }
    public void validateToastSuccess(){
        WebElement toast = driver.findElement(By.xpath("//div[contains(@class,'Toastify__toast Toastify__toast-theme--light')]"));
        toast.isDisplayed();
        Assert.assertEquals("Login realizado com sucesso", toast.getText());
    }

    public void validateToastError(){
        WebElement toast = driver.findElement(By.xpath("//div[contains(@class,'Toastify__toast Toastify__toast-theme--light')]"));
        toast.isDisplayed();
        Assert.assertEquals("Erro ao realizar login: Verifique se o login e a senha estão corretos", toast.getText());
    }
    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isTextPresent(String text) {
        return driver.getPageSource().contains(text);
    }
}

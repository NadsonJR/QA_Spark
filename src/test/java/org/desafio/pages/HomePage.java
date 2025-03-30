package org.desafio.pages;

import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.Buffer;

@Log4j2
public class HomePage {
    private WebDriver driver;

    // Web elements
    private By homePageTitle = By.xpath("//span[@class='title'][contains(text(),'Products')]");
    private By sauceBikeLightBtnAddCart = By.id("add-to-cart-sauce-labs-bike-light");
    private By cart = By.xpath("//span[@class='shopping_cart_badge']");
    private By removeBtnBikeLight = By.id("remove-sauce-labs-bike-light");
    private By itemCart = By.xpath("//div[@class='cart_item']");
    private By cartIcon = By.xpath("//div//a[@class='shopping_cart_link']");
    private By checkoutBtn = By.id("checkout");
    private By firstName = By.id("first-name");
    private By firstLast = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By finishBtn = By.id("finish");
    private By completeCheckout = By.id("checkout_complete_container");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Page actions
    public void validateLogin() {
        WebElement homePageTitleElement = driver.findElement(homePageTitle);
        Assert.assertEquals(homePageTitleElement.getText(),"Products");
    }
    public void addSauceBikeLightToCart() throws InterruptedException {
        Thread.sleep(2000);
        WebElement btnAddCartBikeLight = driver.findElement(sauceBikeLightBtnAddCart);
        btnAddCartBikeLight.click();
        WebElement removeBtnBikeLightElement = driver.findElement(removeBtnBikeLight);
        removeBtnBikeLightElement.isDisplayed();
    }

    public void validateCart() throws InterruptedException {
        Thread.sleep(2000);
        WebElement cartElement = driver.findElement(cart);
        Assert.assertEquals(cartElement.getText(),"1");
    }
    public void openCart() throws InterruptedException {
        Thread.sleep(2000);
        WebElement cartElement = driver.findElement(cartIcon);
        cartElement.click();
    }
    public void verifyCart() throws InterruptedException {
        Thread.sleep(2000);
        WebElement cartElement = driver.findElement(itemCart);
        String itemInfo = cartElement.getText();
        if(itemInfo.contains("Sauce Labs Bike Light")){
            log.info("The product is in the cart");
        }else{
            log.error("The product is not in the cart");
            Assert.fail("The product is not in the cart");
        }
    }

    public void clickCheckoutButton() throws InterruptedException {
        Thread.sleep(2000);
        WebElement checkoutBtnElement = driver.findElement(checkoutBtn);
        checkoutBtnElement.click();
    }

    public void fillZipForm() throws InterruptedException {
        Thread.sleep(2000);
        WebElement firstNameElement = driver.findElement(firstName);
        firstNameElement.sendKeys("John");
        WebElement lastNameElement = driver.findElement(firstLast);
        lastNameElement.sendKeys("Doe");
        WebElement zipElement = driver.findElement(postalCode);
        zipElement.sendKeys("12345");
    }

    public void clickContinueButton() throws InterruptedException {
        Thread.sleep(2000);
        WebElement continueBtnElement = driver.findElement(continueBtn);
        continueBtnElement.click();
    }

    public void validateOverview() throws InterruptedException {
        Thread.sleep(2000);
        WebElement cartElement = driver.findElement(itemCart);
        String itemInfo = cartElement.getText();
        if(itemInfo.contains("Sauce Labs Bike Light")){
            log.info("The product is in the cart");
        }else{
            log.error("The product is not in the cart");
            Assert.fail("The product is not in the cart");
        }
    }
    public void clickFinishButton() throws InterruptedException {
        Thread.sleep(2000);
        WebElement finishBtnElement = driver.findElement(finishBtn);
        finishBtnElement.click();
    }
    public void validateCompleteCheckout() throws InterruptedException {
        Thread.sleep(2000);
        WebElement completeCheckoutElement = driver.findElement(completeCheckout);
        if(completeCheckoutElement.getText().contains("Thank you for your order!")){
            log.info("The order was completed");
        }else{
            log.error("The order was not completed");
            Assert.fail("The order was not completed");
        }
    }

}

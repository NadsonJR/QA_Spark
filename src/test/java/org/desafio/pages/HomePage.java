package org.desafio.pages;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class HomePage {
    private WebDriver driver;
    private Faker faker = new Faker();
    private String ItemCartDescription = "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.";

    // Web elements
    private By homePageTitle = By.xpath("//span[@class='title'][contains(text(),'Products')]");
    private By sauceBikeLightBtnAddCart = By.id("add-to-cart-sauce-labs-bike-light");
    private By cart = By.xpath("//span[@class='shopping_cart_badge']");
    private By removeBtnBikeLight = By.id("remove-sauce-labs-bike-light");
    private By itemCart = By.xpath("//div[@class='cart_item']");
    private By itemCartDescription = By.xpath("//div[@class='inventory_item_desc']");
    private By itemCartPrice = By.xpath("//div[@class='inventory_item_price']");
    private By cartIcon = By.xpath("//div//a[@class='shopping_cart_link']");
    private By checkoutBtn = By.id("checkout");
    private By zipForm = By.xpath("//div[@class='checkout_info']");
    private By firstName = By.id("first-name");
    private By firstLast = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By finishBtn = By.id("finish");
    private By itemTotalPrice = By.className("summary_subtotal_label");
    private By taxPrice = By.className("summary_tax_label");
    private By totalPrice = By.className("summary_total_label");
    private By completeCheckout = By.xpath("//h2[@class=\"complete-header\"]");
    private By completeCheckoutDescription = By.xpath("//div[@class=\"complete-text\"]");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Page actions
    public WebElement validateLogin() {
        WebElement homePageTitleElement = driver.findElement(homePageTitle);
        Assert.assertEquals(homePageTitleElement.getText(),"Products");
        return homePageTitleElement;
    }
    public WebElement addSauceBikeLightToCart() throws InterruptedException {
        Thread.sleep(2000);
        WebElement btnAddCartBikeLight = driver.findElement(sauceBikeLightBtnAddCart);
        btnAddCartBikeLight.click();
        return btnAddCartBikeLight;
    }

    public WebElement validateRemoveBtn() throws InterruptedException {
        Thread.sleep(2000);
        WebElement removeBtnBikeLightElement = driver.findElement(removeBtnBikeLight);
        Assert.assertEquals(removeBtnBikeLightElement.getText(),"Remove");
        return removeBtnBikeLightElement;
    }

    public WebElement validateCart() throws InterruptedException {
        Thread.sleep(2000);
        WebElement cartElement = driver.findElement(cart);
        Assert.assertEquals(cartElement.getText(),"1");
        return cartElement;
    }
    public void openCart() throws InterruptedException {
        Thread.sleep(2000);
        WebElement cartElement = driver.findElement(cartIcon);
        cartElement.click();
    }
    public WebElement verifyCart() throws InterruptedException {
        Thread.sleep(2000);
        WebElement cartElement = driver.findElement(itemCart);
        WebElement itemCartDescriptionElement = driver.findElement(itemCartDescription);
        WebElement itemCartPriceElement = driver.findElement(itemCartPrice);
        if(cartElement.getText().contains("Sauce Labs Bike Light")){
            log.info("The product is in the cart");
        }else{
            log.error("The product is not in the cart");
            Assert.fail("The product is not in the cart");
        }
        if (itemCartDescriptionElement.getText().contains(ItemCartDescription)){
            log.info("The product description is correct");
        }else{
            log.error("The product description is not correct");
            Assert.fail("The product description is not correct");
        }
        if (itemCartPriceElement.getText().contains("$9.99")){
            log.info("The product price is correct");
        }else{
            log.error("The product price is not correct");
            Assert.fail("The product price is not correct");
        }
        return cartElement;
    }

    public void clickCheckoutButton() throws InterruptedException {
        Thread.sleep(2000);
        WebElement checkoutBtnElement = driver.findElement(checkoutBtn);
        checkoutBtnElement.click();
    }

    public WebElement fillZipForm() throws InterruptedException {
        Thread.sleep(2000);
        WebElement zipFormElement = driver.findElement(zipForm);
        WebElement firstNameElement = driver.findElement(firstName);
        firstNameElement.sendKeys(faker.name().firstName());
        WebElement lastNameElement = driver.findElement(firstLast);
        lastNameElement.sendKeys(faker.name().lastName());
        WebElement zipElement = driver.findElement(postalCode);
        zipElement.sendKeys(faker.address().zipCode());
        return zipFormElement;
    }

    public void clickContinueButton() throws InterruptedException {
        Thread.sleep(2000);
        WebElement continueBtnElement = driver.findElement(continueBtn);
        continueBtnElement.click();
    }

    public WebElement validateOverview() throws InterruptedException {
        Thread.sleep(2000);
        WebElement cartElement = driver.findElement(itemCart);
        WebElement itemCartDescriptionElement = driver.findElement(itemCartDescription);
        if(cartElement.getText().contains("Sauce Labs Bike Light")){
            log.info("The product is in the cart");
        }else{
            log.error("The product is not in the cart");
            Assert.fail("The product is not in the cart");
        }
        if (itemCartDescriptionElement.getText().contains(ItemCartDescription)){
            log.info("The product description is correct");
        }else{
            log.error("The product description is not correct");
            Assert.fail("The product description is not correct");
        }
        validatePrices();
        return cartElement;
    }
    public void clickFinishButton() throws InterruptedException {
        Thread.sleep(2000);
        WebElement finishBtnElement = driver.findElement(finishBtn);
        finishBtnElement.click();
    }

    public void validatePrices() throws InterruptedException {
        Thread.sleep(1000);
        WebElement itemCartPriceElement = driver.findElement(itemCartPrice);
        if (itemCartPriceElement.getText().contains("$9.99")){
            log.info("The product price is correct");
        }else{
            log.error("The product price is not correct");
            Assert.fail("The product price is not correct");
        }
        // Get price texts and clean them
        String itemTotal = driver.findElement(itemTotalPrice).getText()
                .replace("Item total: $", "");
        String tax = driver.findElement(taxPrice).getText()
                .replace("Tax: $", "");
        String total = driver.findElement(totalPrice).getText()
                .replace("Total: $", "");

        // Convert to double for comparison
        double itemTotalValue = Double.parseDouble(itemTotal);
        double taxValue = Double.parseDouble(tax);
        double totalValue = Double.parseDouble(total);

        // Expected values
        double expectedItemTotal = 9.99;
        double expectedTax = 0.80;
        double expectedTotal = 10.79;

        if (itemTotalValue == expectedItemTotal){
            log.info("The item total price is correct");
        }else{
            log.error("The item total price is not correct");
            Assert.fail("The item total price is not correct");
        }
        if (taxValue == expectedTax){
            log.info("The tax price is correct");
        }else{
            log.error("The tax price is not correct");
            Assert.fail("The tax price is not correct");
        }
        if (totalValue == expectedTotal){
            log.info("The total price is correct");
        }else{
            log.error("The total price is not correct");
            Assert.fail("The total price is not correct");
        }
    }
    public WebElement validateCompleteCheckout() throws InterruptedException {
        Thread.sleep(2000);
        WebElement completeCheckoutElement = driver.findElement(completeCheckout);
        WebElement completeCheckoutDescriptionElement = driver.findElement(completeCheckoutDescription);
        if(completeCheckoutElement.getText().contains("Thank you for your order!")){
            log.info("The order was completed");
        }else{
            log.error("The order was not completed");
            Assert.fail("The order was not completed");
        }
        if (completeCheckoutDescriptionElement.getText().contains("Your order has been dispatched, and will arrive just as fast as the pony can get there!")){
            log.info("The order description is correct");
        }else{
            log.error("The order description is not correct");
            Assert.fail("The order description is not correct");
        }
        return completeCheckoutElement;
    }
}

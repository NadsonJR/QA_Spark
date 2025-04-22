package org.desafio.pages;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.desafio.utils.DocumentConfig;
import org.desafio.utils.WaitUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class HomePage {
    private WebDriver driver;
    private Faker faker = new Faker();
    private DocumentConfig documentConfig;
    private String ItemCartDescription = "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.";
    private WaitUtils waitUtils;
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
        documentConfig = new DocumentConfig();
        this.driver = driver;
        waitUtils = new WaitUtils(driver);
    }

    // Page actions
    public void validateLogin(String step) {
        WebElement homePageTitleElement = driver.findElement(homePageTitle);
        documentConfig.HighlightElementScreenshot(driver, homePageTitleElement, step);
        Assert.assertEquals(homePageTitleElement.getText(),"Products");
    }
    public void addSauceBikeLightToCart(String step) throws InterruptedException {
        waitUtils.waitForElementToBeVisible(sauceBikeLightBtnAddCart);
        WebElement btnAddCartBikeLight = driver.findElement(sauceBikeLightBtnAddCart);
        documentConfig.HighlightElementScreenshot(driver, btnAddCartBikeLight, step);
        waitUtils.waitForElementToBeClickable(sauceBikeLightBtnAddCart);
        btnAddCartBikeLight.click();
    }

    public void validateRemoveBtn(String step) throws InterruptedException {
        waitUtils.waitForElementToBeVisible(removeBtnBikeLight);
        WebElement removeBtnBikeLightElement = driver.findElement(removeBtnBikeLight);
        documentConfig.HighlightElementScreenshot(driver, removeBtnBikeLightElement, step);
        Assert.assertEquals(removeBtnBikeLightElement.getText(),"Remove");
    }

    public void validateCart(String step) throws InterruptedException {
        waitUtils.waitForElementToBeVisible(cart);
        WebElement cartElement = driver.findElement(cart);
        documentConfig.HighlightElementScreenshot(driver, cartElement, step);
        Assert.assertEquals(cartElement.getText(),"1");
    }
    public void openCart(String step) throws InterruptedException {
        waitUtils.waitForElementToBeVisible(cartIcon);
        WebElement cartElement = driver.findElement(cartIcon);
        documentConfig.HighlightElementScreenshot(driver, cartElement, step);
        cartElement.click();
    }

    public void verifyCartElement(String step){
        WebElement cartElement = driver.findElement(itemCart);
        if(cartElement.getText().contains("Sauce Labs Bike Light")){
            log.info("The product is in the cart");
            documentConfig.HighlightElementScreenshot(driver, cartElement, step);
        }else{
            log.error("The product is not in the cart");
            documentConfig.HighlightElementScreenshot(driver, cartElement, step);
            Assert.fail("The product is not in the cart");
        }
    }
    public void verifyItemDescription(String step){
        WebElement itemCartDescriptionElement = driver.findElement(itemCartDescription);
        if (itemCartDescriptionElement.getText().contains(ItemCartDescription)){
            log.info("The product description is correct");
            documentConfig.HighlightElementScreenshot(driver, itemCartDescriptionElement, step);
        }else{
            log.error("The product description is not correct");
            documentConfig.HighlightElementScreenshot(driver, itemCartDescriptionElement, step);
            Assert.fail("The product description is not correct");
        }
    }

    public void verifyItemCartPriceElement(String step){
        WebElement itemCartPriceElement = driver.findElement(itemCartPrice);
        if (itemCartPriceElement.getText().contains("$9.99")){
            log.info("The product price is correct");
            documentConfig.HighlightElementScreenshot(driver, itemCartPriceElement, step);
        }else{
            log.error("The product price is not correct");
            documentConfig.HighlightElementScreenshot(driver, itemCartPriceElement, step);
            Assert.fail("The product price is not correct");
        }
    }

    public void clickCheckoutButton(String step) throws InterruptedException {
        waitUtils.waitForElementToBeVisible(checkoutBtn);
        WebElement checkoutBtnElement = driver.findElement(checkoutBtn);
        documentConfig.HighlightElementScreenshot(driver, checkoutBtnElement, step);
        checkoutBtnElement.click();
    }

    public void fillZipForm(String step) throws InterruptedException {
        waitUtils.waitForElementToBeVisible(zipForm);
        WebElement zipFormElement = driver.findElement(zipForm);
        WebElement firstNameElement = driver.findElement(firstName);
        firstNameElement.sendKeys(faker.name().firstName());
        WebElement lastNameElement = driver.findElement(firstLast);
        lastNameElement.sendKeys(faker.name().lastName());
        WebElement zipElement = driver.findElement(postalCode);
        zipElement.sendKeys(faker.address().zipCode());
        documentConfig.HighlightElementScreenshot(driver, zipFormElement, step);
    }

    public void clickContinueButton(String step) throws InterruptedException {
        waitUtils.waitForElementToBeVisible(continueBtn);
        WebElement continueBtnElement = driver.findElement(continueBtn);
        documentConfig.HighlightElementScreenshot(driver, continueBtnElement, step);
        continueBtnElement.click();
    }

    public void validateOverview(String step) throws InterruptedException {
        validatePrices();
    }
    public void clickFinishButton(String step) throws InterruptedException {
        waitUtils.waitForElementToBeVisible(finishBtn);
        WebElement finishBtnElement = driver.findElement(finishBtn);
        documentConfig.HighlightElementScreenshot(driver, finishBtnElement, step);
        finishBtnElement.click();
    }

    public void validatePrices() throws InterruptedException {
        waitUtils.waitForElementToBeVisible(itemCartPrice);
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
            documentConfig.HighlightElementScreenshot(driver, driver.findElement(itemTotalPrice),
                    "The item total price is correct");
        }else{
            log.error("The item total price is not correct");
            documentConfig.HighlightElementScreenshot(driver, driver.findElement(itemTotalPrice),
                    "The item total price is not correct");
            Assert.fail("The item total price is not correct");
        }
        if (taxValue == expectedTax){
            log.info("The tax price is correct");
            documentConfig.HighlightElementScreenshot(driver, driver.findElement(taxPrice),
                    "The tax price is correct");
        }else{
            log.error("The tax price is not correct");
            documentConfig.HighlightElementScreenshot(driver, driver.findElement(taxPrice),
                    "The tax price is not correct");
            Assert.fail("The tax price is not correct");
        }
        if (totalValue == expectedTotal){
            log.info("The total price is correct");
            documentConfig.HighlightElementScreenshot(driver, driver.findElement(totalPrice),
                    "The total price is correct");
        }else{
            log.error("The total price is not correct");
            documentConfig.HighlightElementScreenshot(driver, driver.findElement(totalPrice),
                    "The total price is not correct");
            Assert.fail("The total price is not correct");
        }
    }

    public void validateCompleteCheckout() throws InterruptedException {
        waitUtils.waitForElementToBeVisible(completeCheckout);
        WebElement completeCheckoutElement = driver.findElement(completeCheckout);
        WebElement completeCheckoutDescriptionElement = driver.findElement(completeCheckoutDescription);
        if(completeCheckoutElement.getText().contains("Thank you for your order!")){
            log.info("The order was completed");
            documentConfig.HighlightElementScreenshot(driver, completeCheckoutElement,"The order was completed");
        }else{
            log.error("The order was not completed");
            documentConfig.HighlightElementScreenshot(driver, completeCheckoutElement, "The order was not completed");
            Assert.fail("The order was not completed");
        }
        if (completeCheckoutDescriptionElement.getText().contains("Your order has been dispatched, and will arrive just as fast as the pony can get there!")){
            log.info("The order description is correct");
            documentConfig.HighlightElementScreenshot(driver, completeCheckoutDescriptionElement, "The order description is correct");
        }else{
            log.error("The order description is not correct");
            documentConfig.HighlightElementScreenshot(driver, completeCheckoutDescriptionElement, "The order description is not correct");
            Assert.fail("The order description is not correct");
        }
    }
}

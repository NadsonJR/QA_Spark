package org.desafio.logic.pages;

import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class HomePage {

    private WebDriver driver;
    // Web elements
    private By homePageTitle = By.xpath("//span[@class='title'][contains(text(),'Products')]");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Page actions
    public void validateLogin() {
        WebElement homePageTitleElement = driver.findElement(homePageTitle);
        Assert.assertEquals(homePageTitleElement.getText(),"Products");
    }
}

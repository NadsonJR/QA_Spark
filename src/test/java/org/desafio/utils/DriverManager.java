package org.desafio.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DriverManager {
    private static WebDriver driver;
    private static List<WebDriver> drivers = new ArrayList<>();
    public static void quitDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
    public DriverManager() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    public static void addDriver(WebDriver driver) {
        drivers.add(driver);
    }
    public static void quitAllDrivers() {
        for (WebDriver driver : drivers) {
            if (driver != null) {
                driver.quit();
            }
        }
    }
    public static WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
//            driver = new ChromeDriver();
        }
        return driver;
    }
    public void waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

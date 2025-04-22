package org.desafio.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

    public static WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
           // options.addArguments("--headless");
            options.addArguments("--window-size=1280,720");
            options.addArguments("--disable-gpu");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
//            driver = new ChromeDriver();
        }
        return driver;
    }
}

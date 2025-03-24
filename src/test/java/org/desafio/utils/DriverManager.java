package org.desafio.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.ArrayList;
import java.util.List;

public class DriverManager {
    private static WebDriver driver;
    private static List<WebDriver> drivers = new ArrayList<>();



    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    public DriverManager() {
        System.setProperty("webdriver.edge.driver", "C:\\edgedriver_win64\\msedgedriver.exe");
        driver = new EdgeDriver();
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
            driver = new EdgeDriver();
        }
        return driver;
    }
}

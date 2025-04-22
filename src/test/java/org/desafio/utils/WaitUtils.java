package org.desafio.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 10;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    // Espera até que o elemento esteja visível
    public void waitForElementToBeVisible(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    // Espera até que o elemento esteja clicável
    public void waitForElementToBeClickable(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Espera até que um elemento desapareça
    public void waitForElementToDisappear(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }
}

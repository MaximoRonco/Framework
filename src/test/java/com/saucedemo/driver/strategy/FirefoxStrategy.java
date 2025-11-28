package com.saucedemo.driver.strategy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxStrategy implements BrowserStrategy {
    @Override
    public WebDriver getDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}
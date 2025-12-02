package com.saucedemo.driver.strategy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class FirefoxStrategy implements BrowserStrategy {

    private static final Logger logger = LoggerFactory.getLogger(FirefoxStrategy.class);

    @Override
    public WebDriver getDriver() {
        logger.info("Initializing Firefox Driver");
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-notifications");
        logger.debug("FirefoxOptions is configured");
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize();

        logger.info("Firefox Driver Initialized");
        return driver;
    }
}
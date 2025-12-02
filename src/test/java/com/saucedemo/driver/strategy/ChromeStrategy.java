package com.saucedemo.driver.strategy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa. selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChromeStrategy implements BrowserStrategy {

    private static final Logger logger = LoggerFactory.getLogger(ChromeStrategy.class);

    @Override
    public WebDriver getDriver() {
        logger.info("Initializing Chrome Driver");

        WebDriverManager.chromedriver().setup();
        logger.debug("Chromedriver downloaded and configured");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--no-first-run");
        options.addArguments("--disable-sync");
        options.addArguments("--disable-default-apps");
        options.addArguments("autofill-off");
        logger.debug("ChromeOptions configuradas");

        WebDriver driver = new ChromeDriver(options);
        logger.info("ChromeDriver created");

        return driver;
    }
}
package com.saucedemo.driver;

import com.saucedemo.driver.strategy.BrowserStrategy;
import com.saucedemo.driver.strategy.ChromeStrategy;
import com.saucedemo.driver.strategy.FirefoxStrategy;
import com.saucedemo.utils.ConfigReader;
import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static BrowserStrategy browserStrategy;

    private DriverManager() {}

    public static void setStrategy(BrowserStrategy strategy) {
        browserStrategy = strategy;
    }

    public static WebDriver getInstance() {
        if (driver.get() == null) {
            if (browserStrategy == null) {
                String browser = ConfigReader.getInstance().getProperty("browser");
                if ("firefox".equalsIgnoreCase(browser)) {
                    browserStrategy = new FirefoxStrategy();
                } else {
                    browserStrategy = new ChromeStrategy();
                }
            }
            driver.set(browserStrategy.getDriver());
        }
        return driver.get();
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
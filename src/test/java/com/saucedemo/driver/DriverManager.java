package com.saucedemo.driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.saucedemo.driver.strategy.BrowserStrategy;
import com.saucedemo.driver.strategy.ChromeStrategy;
import com.saucedemo.driver.strategy.FirefoxStrategy;
import com.saucedemo.utils.ConfigReader;
import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static BrowserStrategy browserStrategy;
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);


    private DriverManager() {}

    public static void setStrategy(BrowserStrategy strategy) {
        browserStrategy = strategy;
    }

    public static WebDriver getInstance() {
        if (driver.get() == null) {
            if (browserStrategy == null) {
                String browser = ConfigReader.getInstance().getProperty("BROWSER");
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
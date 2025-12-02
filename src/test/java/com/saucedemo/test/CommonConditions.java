package com.saucedemo. test;

import com.saucedemo.driver. DriverManager;
import com.saucedemo.utils.ConfigReader;
import com.saucedemo.utils.DataProvider;
import com.saucedemo.model.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng. annotations.AfterMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CommonConditions {

    private static final Logger logger = LoggerFactory.getLogger(CommonConditions.class);

    protected WebDriver driver;
    protected int timeout;
    protected DataProvider dataProvider;

    @BeforeMethod
    public void setUp() {
        logger.info("========== SETUP: Initializing driver ==========");

        this.timeout = Integer.parseInt(ConfigReader.getInstance().getProperty("TIMEOUT"));
        logger.info("Timeout configurated: {} seconds", timeout);

        this.driver = DriverManager.getInstance();
        this.driver.manage().window().maximize();
        this.dataProvider = new DataProvider();
        logger.info("DataProvider initialized");

        logger.info("========== Driver initialized  ==========");
    }

    @AfterMethod
    public void tearDown() {
        logger.info("========== TEARDOWN: closing driver ==========");
        DriverManager.closeDriver();
        logger.info("========== Driver closed  ==========");
    }

    public WebDriver getDriver() {
        return driver;
    }

    public int getTimeout() {
        return timeout;
    }

    public DataProvider getDataProvider() {
        return dataProvider;
    }
}
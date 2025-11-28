package com.saucedemo. test;

import com.saucedemo.driver.DriverManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonConditions {

    private static final Logger logger = LoggerFactory.getLogger(CommonConditions.class);

    @BeforeMethod
    public void setUp() {
        logger.info("---- Se esta Inicializando driver ----");
        DriverManager.getInstance();
        logger.info("---- Se inicializo el driver ----");
    }

    @AfterMethod
    public void tearDown() {
        logger.info("---- Se cerrando el driver ----");
        DriverManager.closeDriver();
        logger.info("---- Se cerro el Driver ----");
    }
}
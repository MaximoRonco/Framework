package com.saucedemo.test;

import com.saucedemo.page.LoginPage;
import com.saucedemo.driver.DriverManager;
import com.saucedemo.model.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org. assertj.core.api.Assertions.assertThat;

public class LoginEmptyCredentialsTest extends CommonConditions {

    private static final Logger logger = LoggerFactory.getLogger(LoginEmptyCredentialsTest.class);

    @Test
    public void testLoginWithEmptyCredentials(){
        logger.info("Inicio: Test Login con credenciales vacías");

        WebDriver driver = DriverManager.getInstance();
        LoginPage loginPage = new LoginPage(driver);

        logger.info("Abriendo página de login");
        loginPage.open();

        logger.info("Ingresando credenciales válidas");
        User user = new User("standard_user", "secret_sauce");
        loginPage.enterCredentials(user);

        logger.info("Limpiando username");
        loginPage.clearUsername();

        logger.info("Limpiando password");
        loginPage.clearPassword();

        logger.info("Clickeando botón login");
        loginPage.clickLogin();

        logger.info("Obteniendo mensaje de error");
        String errorMessage = loginPage.getErrorMessage();

        logger.info("Validando mensaje de error");
        assertThat(errorMessage)
                .as("El mensaje de error debe contener 'Username is required'")
                .contains("Username is required");

        logger.info("Fin: Test Login con credenciales vacías - PASÓ");
    }
}
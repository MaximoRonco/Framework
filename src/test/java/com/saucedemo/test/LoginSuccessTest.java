package com.saucedemo.test;

import com.saucedemo.page.LoginPage;
import com.saucedemo.driver.DriverManager;
import com.saucedemo.model.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSuccessTest extends CommonConditions {

    private static final Logger logger = LoggerFactory.getLogger(LoginSuccessTest.class);

    @DataProvider(name = "acceptedUsers")
    public Object[][] acceptedUsers() {
        return new Object[][] {
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };
    }

    @Test(dataProvider = "acceptedUsers")
    public void testLoginSuccess(String username, String password) {
        logger.info("Inicio: Test Login exitoso");

        WebDriver driver = DriverManager.getInstance();
        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();
        User user = new User(username, password);
        loginPage.enterCredentials(user);

        loginPage.clickLogin();

        String currentUrl = driver.getCurrentUrl();


        // Esto es para usuarios "locked_out_user" porque vi que lo tiene bloqueado a ese usuario la pagina
        if ("locked_out_user".equals(username)) {
            String errorMessage = loginPage.getErrorMessage();
            assertThat(errorMessage)
                    .as("El usuario locked_out_user debería estar bloqueado")
                    .contains("Sorry, this user has been locked out.");
            logger.info("Login fallido");
        } else {
            assertThat(currentUrl)
                    .as("La URL debe contener 'inventory' para login exitoso")
                    .contains("inventory");
            logger.info("Login exitoso");
        }

        logger.info("Fin: Test Login Success");
    }
}
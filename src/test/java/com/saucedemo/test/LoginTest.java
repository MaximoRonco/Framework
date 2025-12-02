package com.saucedemo.test;

import com.saucedemo.model.User;
import com.saucedemo.page.LoginPage;
import com. saucedemo.utils.DataProvider;
import org. testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends CommonConditions {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    // UC1: LOGIN WITH EMPTY CREDENTIALS
    @org.testng.annotations.DataProvider(name = "emptyUsers")
    public Object[][] emptyUsersData() {
        return DataProvider.emptyUsers();
    }

    @Test(dataProvider = "emptyUsers")
    public void testLoginWithEmptyCredentials(User user) {
        logger.info("=== START: UC1 - Login test with empty credentials ===");

        LoginPage loginPage = new LoginPage(driver, timeout);

        logger.info("Opening login page");
        loginPage.open();

        logger.info("Entering credentials");
        loginPage.enterCredentials(user);

        logger.info("Clearing username");
        loginPage.clearUsername();

        logger.info("Clearing password");
        loginPage.clearPassword();

        logger.info("Clicking login button");
        loginPage.clickLogin();

        logger.info("Getting error message");
        String errorMessage = loginPage.getErrorMessage();

        logger.info("Validating error message");
        assertThat(errorMessage)
                .as("Error message should contain 'Username is required'")
                .contains("Username is required");

        logger.info("=== END: UC1 - Login test with empty credentials - PASSED ===");
    }

    // UC2: LOGIN WITH EMPTY PASSWORD
    @org.testng. annotations.DataProvider(name = "emptyPassword")
    public Object[][] emptyPasswordData() {
        return DataProvider.emptyPassword();
    }

    @Test(dataProvider = "emptyPassword")
    public void testLoginWithEmptyPassword(User user) {
        logger.info("=== START: UC2 - Login test with empty password - username: '{}' ===", user.getUsername());

        LoginPage loginPage = new LoginPage(driver, timeout);

        logger.info("Opening login page");
        loginPage.open();

        logger.info("Entering credentials");
        loginPage.enterCredentials(user);

        logger. info("Clearing password");
        loginPage.clearPassword();

        logger.info("Clicking login button");
        loginPage.clickLogin();

        logger.info("Getting error message");
        String errorMessage = loginPage.getErrorMessage();

        logger.info("Validating error message");
        assertThat(errorMessage)
                . as("Error message should contain 'Password is required'")
                . contains("Password is required");

        logger.info("=== END: UC2 - Login test with empty password - PASSED ===");
    }

    // UC3: SUCCESSFUL LOGIN
    @org.testng.annotations.DataProvider(name = "validUsers")
    public Object[][] validUsersData() {
        return DataProvider.validUsers();
    }

    @Test(dataProvider = "validUsers")
    public void testLoginSuccess(User user) {
        String username = user.getUsername();
        logger.info("=== START: UC3 - Successful login test - username: '{}' ===", username);

        LoginPage loginPage = new LoginPage(driver, timeout);

        logger.info("Opening login page");
        loginPage.open();

        logger.info("Entering credentials for user: {}", username);
        loginPage.enterCredentials(user);

        logger.info("Clicking login button");
        loginPage.clickLogin();

        if ("locked_out_user".equals(username)) {
            String errorMessage = loginPage.getErrorMessage();
            assertThat(errorMessage)
                    .as("User locked_out_user should be locked out")
                    .contains("Sorry, this user has been locked out.");
            logger.info("Login failed correctly for '{}'", username);
        } else {
            String currentUrl = driver.getCurrentUrl();
            assertThat(currentUrl)
                    .as("URL should contain 'inventory' for successful login")
                    .contains("inventory");
            logger.info("Successful login for '{}'", username);
        }

        logger.info("=== END: UC3 - Login test - username: '{}' ===", username);
    }
}
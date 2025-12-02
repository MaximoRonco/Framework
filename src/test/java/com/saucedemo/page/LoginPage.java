package com.saucedemo.page;

import com.saucedemo.model.User;
import com.saucedemo.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa. selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa. selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org. openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public class LoginPage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "[data-test='username']")
    private WebElement username;

    @FindBy(css = "[data-test='password']")
    private WebElement password;

    @FindBy(css = "[data-test='login-button']")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver, int timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        PageFactory.initElements(driver, this);
        logger.info("LoginPage initialized");
    }

    public void open() {
        String baseUrl = ConfigReader.getInstance().getProperty("BASE_URL");
        driver.get(baseUrl);
        logger.info("LoginPage opened");
    }

    public void enterCredentials(User user) {
        enterUsername(user.getUsername());
        enterPassword(user.getPassword());
        logger.info("Credentials entered");
    }

    public void enterUsername(String name) {
        username.clear();
        username.sendKeys(name);
        logger.debug("Username entered");
    }

    public void enterPassword(String pass) {
        password.clear();
        password.sendKeys(pass);
        logger.debug("Password entered");
    }

    public void clickLogin() {
        loginButton.click();
        logger.info("Click on the login button");
    }

    public void clearUsername() {
        username.sendKeys(Keys.CONTROL + "a");
        username. sendKeys(Keys.DELETE);
        logger.debug("Username cleaned");
    }

    public void clearPassword() {
        password.sendKeys(Keys.CONTROL + "a");
        password.sendKeys(Keys.DELETE);
        logger.debug("Password cleaned");
    }


    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            String error = errorMessage.getText();
            logger.error("Captured error: {}", error);
            return error;
        } catch (Exception e) {
            logger.warn("No error message found");
            return "";
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
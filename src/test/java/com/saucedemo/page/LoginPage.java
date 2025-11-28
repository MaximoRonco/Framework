package com.saucedemo.page;

import com.saucedemo.model.User;
import org.openqa.selenium.By;
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

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        logger.info("LoginPage inicializado");
    }

    public void open() {
        driver.get("https://www.saucedemo.com/");
        logger.info("Página de login abierta");
    }

    public void enterCredentials(User user) {
        enterUsername(user.getUsername());
        enterPassword(user.getPassword());
        logger.info("Credenciales ingresadas");
    }

    public void enterUsername(String name) {
        username.clear();
        username.sendKeys(name);
        logger.debug("Username ingresado");
    }

    public void enterPassword(String pass) {
        password.clear();
        password.sendKeys(pass);
        logger.debug("Password ingresado");
    }

    public void clickLogin() {
        loginButton.click();
        logger.info("Click en botón Login");
    }

    public void clearUsername() {
        username.clear();
    }

    public void clearPassword() {
        password.clear();
    }


    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            String error = errorMessage.getText();
            logger.error("Error capturado: {}", error);
            return error;
        } catch (Exception e) {
            logger.warn("No se encontró mensaje de error");
            return "";
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
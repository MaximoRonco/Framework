# Saucedemo Automation Framework

## Task Description

This project is an automated test suite for [Saucedemo](https://www.saucedemo.com/) using **Selenium WebDriver**, **Maven**, **TestNG**, and **AssertJ**. It supports **parallel execution**, **logging with SLF4J**, and uses **DataProvider** to parametrize tests.

---

### User Cases

#### UC-1: Test Login form with empty credentials

- Type any credentials into "Username" and "Password" fields.
- Clear the inputs.
- Hit the "Login" button.
- Check the error message: **"Username is required"**

#### UC-2: Test Login form with credentials by passing Username

- Type any credentials into username.
- Enter password.
- Clear the "Password" input.
- Hit the "Login" button.
- Check the error message: **"Password is required"**

#### UC-3: Test Login form with credentials by passing Username & Password

- Type credentials in username (**Accepted usernames:** `standard_user`, `locked_out_user`, `problem_user`, `performance_glitch_user`, `error_user`, `visual_user`)
- Enter password as **secret_sauce**
- Click on Login and validate the title **"Swag Labs"** in the dashboard.

---

## Features

- **Parallel execution** of tests with TestNG.
- **Logging for test steps** using SLF4J and Log4j2.
- **DataProvider** for parameterized tests.
- Covers all task User Cases: **UC-1**, **UC-2**, **UC-3**.
- **Browsers:** Chrome and Firefox.
- **Locators:** CSS selectors.
- **Test runner:** TestNG.
- **Assertions:** AssertJ.
- [Optional] **Patterns:** Singleton, Adapter, Strategy (can be used and extended).
- [Optional] **BDD approach** (if needed).

---

## How to run

1. Install JDK 11 or higher.
2. Install Maven.
3. Clone this repository.
4. To execute tests:

   ```
   mvn test
   ```

   This will run all tests in parallel as configured in `testng.xml`.

---

## Structure

- `src/test/java` : Test classes and utility code.
- `src/test/resources/testng.xml` : TestNG suite configuration.
- `src/test/resources/log4j2.xml` : Logger configuration.
- `pom.xml` : Maven configuration.
- `README.md` : Task description.

---

## Additional Notes

- Logging is provided by SLF4J with Log4j2 backend.
- Test data is parametrized via TestNG Data Providers.
- Patterns and BDD support can be added as per requirements.
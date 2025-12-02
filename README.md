# Saucedemo Automation Framework

## General Explanation

This is an automated testing framework for [Saucedemo](https://www.saucedemo.com/). It tests the login functionality with multiple scenarios using **Selenium WebDriver**, **TestNG**, and **AssertJ**.

The framework validates:
- **UC-1:** Login with empty credentials (both username and password empty)
- **UC-2:** Login with empty password (only password empty)
- **UC-3:** Successful login with valid credentials (6 different users)

Tests run in **parallel** with detailed **logging** for easy debugging.

---

## Project Structure

```
saucedemo/
├── src/test/java/com/saucedemo/
│   ├── test/
│   │   ├── CommonConditions.java      ← Base class (setup/teardown)
│   │   └── LoginTest.java             ← All 3 test cases
│   │
│   ├── page/
│   │   └── LoginPage.java             ← Page Object (UI interactions)
│   │
│   ├── model/
│   │   └── User.java                  ← User data model
│   │
│   ├── driver/
│   │   ├── DriverManager.java         ← WebDriver singleton
│   │   └── strategy/
│   │       ├── BrowserStrategy.java
│   │       ├── ChromeStrategy.java
│   │       └── FirefoxStrategy.java
│   │
│   └── utils/
│       ├── ConfigReader.java          ← Read config. properties
│       └── DataProvider.java          ← Test data provider
│
├── src/test/resources/
│   ├── config.properties              ← Configuration (browser, timeout, URL)
│   └── log4j2.xml                     ← Logging configuration
│
├── testng.xml                         ← TestNG suite configuration
├── pom.xml                            ← Maven dependencies
└── README. md                          ← This file
```

**Key Components:**
- **LoginTest.java**: Contains all 3 test cases (UC-1, UC-2, UC-3)
- **LoginPage.java**: Handles all interactions with the login page
- **CommonConditions.java**: Base class with setup/teardown for all tests
- **DriverManager.java**: Manages WebDriver instances (Singleton pattern with ThreadLocal)
- **DataProvider.java**: Provides test data for parameterized tests
- **config.properties**: Centralized configuration (browser, timeout, base URL)

---

## How to Run

### Prerequisites
- **JDK 11** or higher
- **Maven 3.6** or higher
- **Git**

### Step 1: Clone the Repository
```bash
git clone <repository-url>
cd saucedemo
```

### Step 2: Install Dependencies
```bash
mvn clean install
```

### Step 3: Run All Tests
```bash
mvn clean test
```

This will run all 3 test cases **in parallel** and display results.

### Run Specific Test Case

**UC-1: Empty credentials**
```bash
mvn test -Dtest=LoginTest#testLoginWithEmptyCredentials
```

**UC-2: Empty password**
```bash
mvn test -Dtest=LoginTest#testLoginWithEmptyPassword
```

**UC-3: Successful login**
```bash
mvn test -Dtest=LoginTest#testLoginSuccess
```

### Change Browser

Edit `src/test/resources/config.properties`:
```properties
BROWSER=chrome    # Change to: chrome or firefox
```

Then run:
```bash
mvn clean test
```

### Change Timeout

Edit `src/test/resources/config.properties`:
```properties
TIMEOUT=15    # Increase if tests are slow
```

---

## Configuration

File: `src/test/resources/config.properties`

```properties
BROWSER=firefox              # Browser: firefox or chrome
BASE_URL=https://www.saucedemo.com/    # Application URL
TIMEOUT=10                   # WebDriverWait timeout in seconds
```

---

## Test Results

After running tests, you'll see:
```
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 15.234 s
[INFO] BUILD SUCCESS
```

Detailed logs are printed in the console during execution.

---

## Features

✅ Parallel test execution (all 3 tests run simultaneously)  
✅ Detailed logging (SLF4J + Log4j2)  
✅ DataProvider for multiple user scenarios  
✅ Chrome and Firefox browser support  
✅ Centralized configuration  
✅ Page Object Model pattern  
✅ Singleton pattern for WebDriver management  
✅ Strategy pattern for browser selection  
✅ AssertJ for fluent assertions

---

## Troubleshooting

**Tests fail on Chrome (fields not clearing properly):**
→ The framework uses keyboard shortcuts (`CTRL+A` + `DELETE`) to clear fields


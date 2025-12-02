package com.saucedemo.utils;

import com.saucedemo.model.User;

public class DataProvider {

    public static Object[][] emptyUsers() {
        return new Object[][] {
                { new User("", "") }
        };
    }

    // DataProvider for empty password
    public static Object[][] emptyPassword() {
        return new Object[][] {
                { new User("standard_user", "") }
        };
    }

    // DataProvider for successful login
    public static Object[][] validUsers() {
        return new Object[][] {
                { new User("standard_user", "secret_sauce") },
                { new User("locked_out_user", "secret_sauce") },
                { new User("problem_user", "secret_sauce") },
                { new User("performance_glitch_user", "secret_sauce") },
                { new User("error_user", "secret_sauce") },
                { new User("visual_user", "secret_sauce") }
        };
    }
}
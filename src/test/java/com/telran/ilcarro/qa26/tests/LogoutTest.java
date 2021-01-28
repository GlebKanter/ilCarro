package com.telran.ilcarro.qa26.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if(!app.getUserHelper().isUserLoggedIn()){
           app.getUserHelper().logIn(app.setEmail(), app.setPassword());
        }
    }
    @Test
    public void logoutTest() {
        app.getUserHelper().clickLogoutButtonOnHeader();
        Assert.assertFalse(app.getUserHelper().isUserLoggedIn());

    }

}

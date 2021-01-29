package com.telran.ilcarro.qa26.tests;

import com.telran.ilcarro.qa26.application.DataProviders;
import com.telran.ilcarro.qa26.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;

public class LoginTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        /*if user logged in, logout*/
        if (app.getUserHelper().isUserLoggedIn()) {
            app.getUserHelper().clickLogoutButtonOnHeader();
        }
        By locator = By.xpath("//a[normalize-space()='Log in']");

        app.getUserHelper().click(locator);
        app.wd.findElement(By.cssSelector("[name=email]")).clear();
        app.wd.findElement(By.cssSelector("[name=password]")).clear();

    }

    private void testForTestAndScreen() throws IOException {
        Assert.assertTrue(app.getUserHelper().isUserLoggedIn());

        logger.info("test passed");
        String screenshot = "src/test/sсreenshots/screenshot-" + System.currentTimeMillis() + ".png";
        app.getUserHelper().takeScreenshot(screenshot);
        logger.info("created screenshot " + screenshot);
    }

    @Test(dataProvider = "validLoginFromList", dataProviderClass = DataProviders.class)
    public void validLoginFromListTest(String email, String password) throws IOException, InterruptedException {
        User user = new User();
        app.getUserHelper().type(user.withEmail(email).withPassword(password));
        System.out.println(user.toString());
        app.getUserHelper().fillLoginForm(user);
        app.getUserHelper().pause(2000);
        app.getUserHelper().clickYallaButton();

        testForTestAndScreen();
    }

    @Test(dataProvider = "loginFromValidLoginCsvFile", dataProviderClass = DataProviders.class)

    public void loginFromValidLoginCsvFileTest(User user) throws IOException {
        app.getUserHelper().type(user);
        System.out.println(user);
        app.getUserHelper().fillLoginForm(user);
        app.getUserHelper().clickYallaButton();
        testForTestAndScreen();
    }


    @Test
    public void testLoginWithValidData() throws InterruptedException, IOException {
        logger.info(" \n user name: my.email1608881337043@gmail.com" +
                " \n user pwd is Aa1234567");

        app.getUserHelper().clickLogInButton();
        app.getUserHelper().fillLoginForm(new User()
                .withEmail("my.email1608881337043@gmail.com")
                .withPassword("Aa1234567"));
        app.getUserHelper().pause(2000);
        app.getUserHelper().clickYallaButton();
        testForTestAndScreen();
    }

    @Test
    public void testForgotPassword() {
        String email = "my.email1608881337043@gmail.com";
        app.getUserHelper().clickForgotPassword();
        app.getUserHelper().fillEmail(email);
        app.getUserHelper().clickRestore();
        Assert.assertTrue(app.getUserHelper().isElementPresent(By.xpath("//p[normalize-space()='Please, check the mail.']")));
    }
}

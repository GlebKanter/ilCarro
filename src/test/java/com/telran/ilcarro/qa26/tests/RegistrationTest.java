package com.telran.ilcarro.qa26.tests;


import com.telran.ilcarro.qa26.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase {

    @Test
    public void testRegistration() throws InterruptedException {
        if (app.getUserHelper().isUserLoggedIn()) {
            app.getUserHelper().logOut();
        }
        app.getUserHelper().openRegForm();
        String email = "my.email" + System.currentTimeMillis() + "@mail.com";
        System.out.println(email + ";iL12345678");
        app.getUserHelper().fillRegistrationForm(new User()
                .withfName("Gleb")
                .withlName("Ka")
                .withEmail(email)
                .withPassword("iL12345678"));

        app.getUserHelper().selectCheckBox();
        app.getUserHelper().pause(2000);
        app.getUserHelper().clickYallaButton();
        app.getUserHelper().pause(3000);

        Assert.assertFalse(app.getUserHelper().isRegistrationFormPresent());

    }




    @Test
    public void testRegistrationNegative() throws InterruptedException {
        if (app.getUserHelper().isUserLoggedIn()) {
            app.getUserHelper().logOut();
        }
        app.getUserHelper().openRegForm();
        String email = "my.email" + System.currentTimeMillis() + "@mail";
        app.getUserHelper().fillRegistrationForm(new User()
                .withfName("Gleb")
                .withlName("Ka")
                .withEmail(email)
                .withPassword("Aa1234567"));

        app.getUserHelper().selectCheckBox();
        app.getUserHelper().pause(2000);
        app.getUserHelper().clickYallaButton();
        app.getUserHelper().pause(3000);

        Assert.assertTrue(app.getUserHelper().isRegistrationFormPresent());

    }

    @Test
    public void testRegistrationNegative2() throws InterruptedException {
        app.getUserHelper().openRegForm();
        String email = "my.email" + System.currentTimeMillis() + "@@mail.com";
        app.getUserHelper().fillRegistrationForm(new User()
                .withfName("Gleb")
                .withlName("Ka")
                .withEmail(email));
        app.getUserHelper().selectCheckBox();
        app.getUserHelper().pause(2000);
        app.getUserHelper().clickYallaButton();
        app.getUserHelper().pause(3000);

        Assert.assertTrue(app.getUserHelper().isRegistrationFormPresent());

    }

}



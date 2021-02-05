package com.telran.ilcarro.qa26.tests;

import com.telran.ilcarro.qa26.model.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class CarCreationTests extends TestBase {

        Car testcar = new Car()
                .withCountry("Israel")
                .withAddress("Rehovot Herzel")
                .withDistance("2000")
                .withSerNumber("456-35-328")
                .withBrand("B")
                .withModel("AS45")
                .withYear("2018")
                .withEngine("IC")
                .withFuelCon("24.9")
                .withFuel("diesel")
                .withTransmission("Automatic")
                .withtWeelDrive("AWD")
                .withHorsePower("5400")
                .withTorque("345")
                .withDoors("4")
                .withSeats("4")
                .withC_lass("A")
                .withAbout("kmo hadash")
                .withFeatures("Wi-Fi hotspot")
                .withPriceDay("300")
                .withPath("C:\\Users\\GLEB\\Documents\\GitHub\\ilCarro_glebKanter_QA26\\src\\test\\resources\\auto_pic.jpeg");


        public void fillCarWorkForm () throws IOException, InterruptedException {
            app.getCarHelper().initAddingNewCar();
            Assert.assertTrue(app.getCarHelper().isCarCreationFormPresent());

            app.getCarHelper().fillCarForm(testcar);
            app.getUserHelper().pause(2000);
            app.getCarHelper().clickYallaButton();
            
        }

    @Test(enabled = true)
    public void testCarWorkIfUserLoggedIn() throws InterruptedException, IOException {
        if (!app.getUserHelper().isUserLoggedIn()) {
            app.getUserHelper().logIn(app.setEmail(), app.setPassword());
        }
        app.getUserHelper().pause(2000);
        fillCarWorkForm();
    }

    @Test(enabled = true)
    public void testCarWorkIfUserLoggedOut() throws InterruptedException, IOException {
        if (app.getUserHelper().isUserLoggedIn()) {
            app.getUserHelper().logOut();
        }
        app.getUserHelper().pause(2000);
        fillCarWorkForm();
    }

    @Test//(enabled = false)
    public void testFindCar() throws InterruptedException {
        if (!app.getUserHelper().isUserLoggedIn()) {
            app.getUserHelper().logIn(app.setEmail(), app.setPassword());
        }
        app.getUserHelper().type(By.xpath("//input[@placeholder='location']"), testcar.getAddress());
        app.getUserHelper().typeFromTillDate("2021-01-27", "2021-02-06");
        app.getUserHelper().pause(2000);
        app.getUserHelper().clickYallaButton();

    }
}

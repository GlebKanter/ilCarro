package com.telran.ilcarro.qa26.application;

import com.telran.ilcarro.qa26.model.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class CarHelper extends HelperBase {
    public CarHelper(WebDriver wd) {
        super(wd);
    }

    public void initAddingNewCar() {
        click(By.cssSelector("header [href='/car']"));
    }

    public boolean isCarCreationFormPresent() {
        return isElementPresent(By.xpath("//h3[contains(., 'Let the car work')]"));
    }


    public void fillCarForm(Car car) throws IOException, InterruptedException {
        type(By.name("country"), car.getCountry());
        type(By.name("address"), car.getAddress());
        type(By.name("distance_included"), car.getDistance());
        type(By.name("serial_number"), car.getSerNumber());
        type(By.name("make"), car.getBrand());
        type(By.name("model"), car.getModel());
        type(By.name("year"), car.getYear());
        type(By.name("engine"), car.getEngine());
        type(By.name("fuel_consumption"), car.getFuelCon());
        type(By.name("fuel"), car.getFuel());
        type(By.name("gear"), car.getTransmission());
        type(By.name("wheels_drive"), car.getWeelDrive());
        type(By.name("horsepower"), car.getHorsePower());
        type(By.name("torque"), car.getTorque());
        type(By.name("doors"), car.getDoors());
        type(By.name("seats"), car.getSeats());
        scrollDown();
        type(By.name("car_class"), car.getC_lass());
        type(By.name("about"), car.getAbout());
        type(By.name("features"), car.getFeatures());
        type(By.name("price_per_day"), car.getPriceDay());
      attachPhoto(By.cssSelector("input[value='Submit']"), new File(car.getPath()));
    }
}

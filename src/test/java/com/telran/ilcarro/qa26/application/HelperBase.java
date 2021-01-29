package com.telran.ilcarro.qa26.application;

import com.telran.ilcarro.qa26.model.User;
import com.google.common.io.Files;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;



public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void typeByCss(String cssSelector, String text) {
        if (text != null) {
            clickByCss(cssSelector);
            wd.findElement(By.cssSelector(cssSelector)).clear();
            wd.findElement(By.cssSelector(cssSelector)).sendKeys(text);
        }

    }

    public void attachPhoto(By locator, File file) throws IOException, InterruptedException {
        if (file != null) {
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
            System.out.println("Photo=done!");
            pause(5000);
            click(By.cssSelector("input[value='Submit']"));
        }
    }
    public void type(User user) {

        System.out.println("I can fill fild1 ->"+user.getEmail());
        System.out.println("I can fill fild2 ->"+user.getPassword());
    }
    public void clickByCss(String cssSelector) {
        wd.findElement(By.cssSelector(cssSelector)).click();
    }

    public void clickByXpath(String xpath) {
        wd.findElement(By.xpath(xpath)).click();
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void clickForgotPassword(){
click(By.xpath("//span[normalize-space()='Click here']"));
    }
    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(text);
        }
    }

    public void selectElement(By locator) {
        WebElement selectElem = wd.findElement(locator);
        selectElem.click();
        String os = System.getProperty("os.name");
        System.out.println(os);
        if (os.startsWith("Mac")) {
            selectElem.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        } else {
            selectElem.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        }
    }

    public void typeFromTillDate(String fromDate, String tillDate) {
        WebElement selectElem = wd.findElement(By.xpath("//input[@placeholder='From']"));
        selectElement(By.xpath("//input[@placeholder='From']"));
        selectElem.sendKeys(fromDate);
        selectElem.sendKeys(Keys.ENTER);scrollDown();
        selectElem = wd.findElement(By.xpath("//input[@placeholder='Till']"));
        selectElement(By.xpath("//input[@placeholder='Till']"));
        selectElem.sendKeys(tillDate);
        selectElem.sendKeys(Keys.ENTER);

    }

    public boolean isElementPresent(By locator) {
        return wd.findElements(locator).size() > 0;
    }

    public void pause(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("window.scrollBy(0,300)");
    }

    public void scrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("window.scrollBy(300,0)");
    }

    public void clickYallaButton() {
        click(By.cssSelector("[type='submit']"));
    }

    public String getPageUrl() {
        return wd.getCurrentUrl();
    }

    public void takeScreenshot(String pathToFile) throws IOException {
        File tmp = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
        File screenshot =
                new File(pathToFile);
        Files.copy(tmp, screenshot);
    }

    public void fillEmail(String email) {
      By locator = (By.cssSelector("input[placeholder='Email']")) ;
      click(locator);
        type(locator, email);


    }

    public void clickRestore() {
        clickByCss("input[value='Restore']");
    }
}

package com.telran.ilcarro.qa26.application;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    Properties properties;
    public static EventFiringWebDriver wd;
    UserHelper userHelper;
    CarHelper carHelper;



    LoginHelper loginHelper;
    String browser;



    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);



    public String setPassword() {
        return properties.getProperty("web.password");
    }

    public String setEmail() {return properties.getProperty("web.email");
    }

    public static class MyListener extends AbstractWebDriverEventListener {
        public MyListener() {
            super();
        }

        Logger logger = LoggerFactory.getLogger(MyListener.class);

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver wd) {

            System.out.println("start search " + by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver wd) {
            System.out.println(by + " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver wd) {
            logger.error(throwable.toString());

            File tmp = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
            File screen =
                    new File("screen" + System.currentTimeMillis() + ".png");
            try {
                Files.copy(tmp, screen);
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.error("Screenshot with error: " + screen.getAbsolutePath());
        }
    }


    public ApplicationManager(String browser) {

        this.browser = browser;
        properties = new Properties();
    }


    public void start() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));


        if (browser.equals(BrowserType.CHROME)) {
            wd = new EventFiringWebDriver(new ChromeDriver());
        } else if (browser.equals(BrowserType.FIREFOX)) {
            wd = new EventFiringWebDriver(new FirefoxDriver());
        } else if (browser.equals(BrowserType.EDGE)) {
            wd = new EventFiringWebDriver(new EdgeDriver());
        }
        wd.register(new MyListener());

        wd.navigate().to(properties.getProperty("web.baseURL"));//"https://ilcarro-dev-v1.firebaseapp.com/"
        logger.info("site: " + wd.getCurrentUrl());//gradlew -Pbrowser=firefox -Ptarget-google clean regression
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        userHelper = new UserHelper(wd);
        carHelper = new CarHelper(wd);
        loginHelper = new LoginHelper(wd);


    }


    public void stop() {
        wd.quit();
    }

    public LoginHelper getLoginHelper() {
        return loginHelper;
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public CarHelper getCarHelper() {
        return carHelper;
    }


}

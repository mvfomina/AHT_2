package com.amazon.testing;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;

import java.util.Locale;

public class DriverManager {
    private static final Logger log = LogManager.getLogger(DriverManager.class.getSimpleName());
    private static DriverManager instance = null;
    private static WebDriver chromeDriver = null;
    private static WebDriver fireFoxDriver = null;
    private static WebDriver edgeDriver = null;

    private DriverManager(){
        log.info("New driver manager created");
    }

    public static WebDriver getDriver(String driverType){
        if(instance == null)
            instance = new DriverManager();
        WebDriver requestedDriver = null;
    if (driverType.toUpperCase(Locale.ROOT).equals("CHROME")){
        log.info("Chrome driver selected");
        requestedDriver = chromeDriver == null ? instance.initChrome() : chromeDriver;
    }
    else if (driverType.toUpperCase(Locale.ROOT).equals("FIREFOX")){
        log.info("Firefox driver selected");
        requestedDriver = fireFoxDriver == null ? instance.initFirefox() : fireFoxDriver;
    }
        return requestedDriver;
    }

    private WebDriver initChrome(){
        log.info("Setting up new ChromeDriver");
        System.setProperty("webdriver.chrome.driver", System.getProperty("driver.path") + "/chromedriver.exe");
        chromeDriver = new ChromeDriver();
        return chromeDriver;
    }
    private WebDriver initFirefox(){
        //firefox requires not only browser to be installed but also profile to be created.
        log.info("Setting up new FirefoxDriver");
        System.setProperty("webdriver.gecko.driver", System.getProperty("driver.path") + "/geckodriver.exe");
        FirefoxProfile profile = new ProfilesIni().getProfile("TestAutomation");
        FirefoxOptions ffOptions = new FirefoxOptions();
        ffOptions.setProfile(profile);
        fireFoxDriver = new FirefoxDriver(ffOptions);
        return fireFoxDriver;
    }

    }


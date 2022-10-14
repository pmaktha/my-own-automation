package com.heroku;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.Arrays;

public class BaseClass {
    //String BaseUrl="https://the-internet.herokuapp.com/";
    String BaseUrl="https://demoqa.com/";
    public static WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
        driver=new ChromeDriver(options);
        driver.get(BaseUrl);
        driver.manage().window().maximize();
    }

    @AfterMethod(enabled = false)
    public void tearDown(){
        driver.quit();
    }

}

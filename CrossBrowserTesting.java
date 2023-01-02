package com.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserTesting {

    WebDriver driver;
    ChromeOptions chromeoptions = new ChromeOptions();
    EdgeOptions edgeOptions = new EdgeOptions();
    FirefoxOptions firefoxOptions = new FirefoxOptions();

    @Parameters("browser")
    @BeforeTest
    public void setUp(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            System.out.println("Chrome browser is launched");
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            System.out.println("Edge browser is launched");
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            System.out.println("Firefox browser is launched");
        }
    }

    @Test
    public void amazonLogIn() {
        driver.get("https://www.amazon.com");
        driver.manage().window().maximize();
        String actual = driver.getTitle();
        String expected = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(actual,expected,"Both titles not matching");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}

package com.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Time;
import java.time.Duration;

public class PracticeTests {

    WebDriver driver;
    ChromeOptions options = new ChromeOptions();

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @Test
    public void applicationForm() {
        driver.get("https://www.techlistic.com/p/selenium-practice-form.html");
      /*  WebElement firstName = driver.findElement(RelativeLocator.with(By.name("firstname"))
                .toRightOf(By.xpath("//span[text()='First name:']"))).sendKeys("Prashanth");*/



    }

    @AfterTest(enabled = false)
    public void tearDown(){
        driver.quit();
    }
}

package com.heroku;

import com.google.gson.internal.bind.util.ISO8601Utils;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestClass extends BaseClass {

    @Test(enabled = false)
    public void sortingTest() {
        driver.findElement(By.linkText("Sortable Data Tables")).click();
        WebElement lastName = driver.findElement(By.xpath("(//span[contains(text(),'Last Name')])[1]"));
        List<WebElement> lastNameData = driver.findElements(By.xpath("//table[@id='table1']//tbody//td"));
        if (lastNameData.size() > 0) {
            for (WebElement ele : lastNameData) {
                if (ele.getText().equalsIgnoreCase("Smith")) {
                    try {
                        Thread.sleep(4000);
                        lastName.click();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (ele.getText().equalsIgnoreCase("Bach")) {
                        try {
                            Thread.sleep(4000);
                            lastName.click();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Test(enabled = false)
    public void basicAuth() {
        driver.findElement(By.linkText("Basic Auth")).click();
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
    }

    @Test(enabled = false)
    public void contextMenu() {
        driver.findElement(By.linkText("Context Menu")).click();
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("hot-spot"))).contextClick()
                .build().perform();
    }

    @Test(enabled = false)
    public void brokenLinks() throws IOException, InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)", "");
        driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[1]")).click();
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Broken Links - Images']")));
        driver.findElement(By.xpath("//span[normalize-space()='Broken Links - Images']")).click();
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total number of links are: " + links.size());
        for (int i = 1; i <= links.size(); i++) {
            WebElement element = links.get(i);
            String url = element.getAttribute("href");
            URL link = new URL(url);
            HttpURLConnection httpconn = (HttpURLConnection) link.openConnection();
            Thread.sleep(3000);
            httpconn.connect();
            int responseCode = httpconn.getResponseCode();
            if (responseCode >= 400) {
                System.out.println(url + " - " + " is broken link");
            } else {
                System.out.println(url + " - " + " is valid link");
            }
        }
    }

    @Test
    public void checkBox() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        System.out.println(jse);
        Thread.sleep(3000);
        jse.executeScript("window.scrollBy(0,250)", "");
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[1]")).click();
        driver.findElement(By.xpath("//span[text()='Check Box']")).click();
        WebElement checkBox = driver.findElement(By.xpath("//*[local-name()='svg']//preceding::span[@class='rct-checkbox']"));
        if (checkBox.isDisplayed() && checkBox.isEnabled()) {
            Thread.sleep(4000);
            checkBox.click();
            WebElement text=driver.findElement(By.xpath("//div[@id='result']"));
            System.out.println(text.getText());
        } else {
            System.out.println("check box is already selected");
        }
    }
}



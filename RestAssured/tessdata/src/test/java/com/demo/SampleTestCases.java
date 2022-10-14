package com.demo;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;


public class SampleTestCases {

    @Test
    public static void fileUpload() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://www.monsterindia.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        WebElement uploadButton=driver.findElement(By.xpath("//span[normalize-space()='Upload Resume']"));
        uploadButton.click();
        Thread.sleep(2000);
        /*WebElement selectFileButton=driver.findElement(By.xpath("//input[@id='file-upload']"));
        Thread.sleep(2000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();",selectFileButton);
        Thread.sleep(2000);*/
        WebElement element=driver.findElement(By.xpath("//input[@id='file-upload']"));
        element.sendKeys("C:\\Users\\mprashanthreddy\\Desktop\\Prashanth\\Deloitte.txt");
        Thread.sleep(2000);
    }

    @Test
    public static void encodingAndDecodingString(){
        String str="Eagle@4951";
        byte[] encodedString=Base64.encodeBase64(str.getBytes());
        System.out.println("encoded String: "+new String(encodedString));

        byte[] decodedString=Base64.decodeBase64(encodedString);
        System.out.println("decoded String: "+new String(decodedString));
    }
}

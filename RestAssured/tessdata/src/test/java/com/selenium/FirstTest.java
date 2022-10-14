package com.selenium;

import com.Utils.Utils;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
//import static io.restassured.RestAssured.*;
import io.restassured.RestAssured.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class FirstTest extends Utils {
    static WebDriver driver;

    private static String requestBody = " {\n" +
            "    \"email\": \"eve.holt@reqres.in\",\n" +
            "    \"password\": \"cityslicka\"\n" +
            "}";

    @Test(enabled = true)
    public void timeSheetSubmission() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://www.fieldglass.net/");
        driver.manage().window().maximize();
        /*driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));*/
        driver.findElement(By.id("usernameId_new")).sendKeys("Prashu123");
        driver.findElement(By.id("passwordId_new")).sendKeys(decodePassword("RWFnbGVANDk1MQ=="));
        driver.findElement(By.xpath("//button[normalize-space()='Sign In']")).submit();
        driver.findElement(By.xpath("(//*[local-name()='svg']//preceding::a)[5]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[text()='Time Sheets']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[text()='All']")).click();
        Thread.sleep(3000);
        WebElement dropdown = driver.findElement(By.xpath("//span[text()='Draft']"));
        if (dropdown.getText().equalsIgnoreCase("Draft")) {
            dropdown.click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//div[@class='jqx-grid-cell-left-align']//a")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//input[@value='Edit']")).click();
            Thread.sleep(2000);

            WebElement wfo = driver.findElement(By.xpath("(//td[normalize-space()='Time Worked'])[1]"));
            String WFO = wfo.getText();
            Assert.assertEquals(WFO, "Time Worked");

            List<WebElement> list = driver.findElements(By.tagName("td"));
            WebElement mondayHours = driver.findElement(By.xpath("(//td)[14]//input[contains(@class,'hour timeEntry')]"));
            WebElement mondayMinutes = driver.findElement(By.xpath("(//td)[14]//input[contains(@class,'min timeEntry')]"));
            WebElement tuesdayHours = driver.findElement(By.xpath("(//td)[15]//input[contains(@class,'hour timeEntry')]"));
            WebElement tuesdayMinutes = driver.findElement(By.xpath("(//td)[15]//input[contains(@class,'min timeEntry')]"));
            WebElement wednesdayHours = driver.findElement(By.xpath("(//td)[16]//input[contains(@class,'hour timeEntry')]"));
            WebElement wednesdayMinutes = driver.findElement(By.xpath("(//td)[16]//input[contains(@class,'min timeEntry')]"));
            WebElement thursdayHours = driver.findElement(By.xpath("(//td)[17]//input[contains(@class,'hour timeEntry')]"));
            WebElement thursdayMinutes = driver.findElement(By.xpath("(//td)[17]//input[contains(@class,'min timeEntry')]"));
            WebElement fridayHours = driver.findElement(By.xpath("(//td)[18]//input[contains(@class,'hour timeEntry')]"));
            WebElement fridayMinutes = driver.findElement(By.xpath("(//td)[18]//input[contains(@class,'min timeEntry')]"));

            if (list.size() > 0)
                mondayHours.sendKeys("9");
            mondayMinutes.sendKeys("0");
            tuesdayHours.sendKeys("9");
            tuesdayMinutes.sendKeys("0");
            wednesdayHours.sendKeys("9");
            wednesdayMinutes.sendKeys("0");
            thursdayHours.sendKeys("9");
            thursdayMinutes.sendKeys("0");
            fridayHours.sendKeys("9");
            fridayMinutes.sendKeys("0");
            Thread.sleep(3000);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollTo(0,document.body.scrollHeight);", "");
            WebElement cb =
                    driver.findElement(RelativeLocator
                            .with(By.xpath("//div[normalize-space()='No Attachments Defined']//following::div//following::div//following::div//following::Div//input[@value='Continue']"))
                            .toLeftOf(By.linkText("Cancel")));
            jse.executeScript("window.scrollTo(0,document.body.scrollHeight);", "");
            Thread.sleep(3000);
            WebElement cb1 = driver.findElement(By.xpath("//input[@type='button' and @value='Continue']"));
            jse.executeScript("window.scrollTo(0,document.body.scrollHeight);", "");
            Thread.sleep(3000);
            cb.click();
            try {
                driver.navigate().refresh();
            } catch (Exception e) {
                e.getMessage();
            }
            WebElement submitButton = driver.findElement(By.xpath("//input[@type='button' and @value='Submit']"));
            if (submitButton.isDisplayed()) {
                Thread.sleep(5000);
                submitButton.click();
                Thread.sleep(5000);
                String title = driver.getTitle();
                System.out.println("Before Confirmation Title: " + title);
                WebElement confirmation = driver.findElement(By.xpath("(//div[text()='Confirmation']//following::form//following::div//following::input)[1]"));
                Thread.sleep(3000);
                confirmation.click();
                String title1 = driver.getTitle();
                System.out.println("After Confirmation title: " + title1);
                WebElement successMessage = driver.findElement(By.xpath("//div[text()='Success!']"));
                String finalMessage = successMessage.getText();
                Assert.assertEquals(finalMessage, "Success!");

            }
        }
    }

    @Test
    public void getRestAssured() {
        RestAssured.
                given().
                baseUri("https://reqres.in").
                header("Accept", "*/*").
                when().
                get("/api/users/1").
                then().
                log().all().assertThat().statusCode(200);
        //Assert.assertEquals(200,200);
    }

    @Test
    public void extractResponse() {
        //RestAssured.responseSpecification.response();
        Response res = RestAssured.given()
                .baseUri("https://reqres.in")
                .header("Connection", "keep-alive")
                .and()
                .body(requestBody)
                .when()
                .post("/api/login")
                .then()
                .log().all().
                extract().response();
        System.out.println("response = " + res.asString());
    }

    @Test
    public void qrCode() throws Exception {
        WebDriverManager.chromedriver().setup();
        /*ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--headless");*/
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://en.wikipedia.org/wiki/QR_code");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//img[@class='thumbimage'])[1]")));
        String locationURL = driver.findElement(By.xpath("(//img[@class='thumbimage'])[1]")).getAttribute("src");
        URL url = new URL(locationURL);
        BufferedImage imageIO = ImageIO.read(url);
        LuminanceSource source = new BufferedImageLuminanceSource(imageIO);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result = new MultiFormatReader().decode(bitmap);
        System.out.println("QR information is: " + result.getText());
    }
    @Test
    public void calenderTest() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.navigate().to("https://www.redbus.in/");
        //driver.get("https://www.redbus.in/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//label[normalize-space()='Date']")).click();
       /* List<WebElement> list = driver.findElements(By.xpath("//table/tbody/tr/td"));
        if(list.size()>0) {
            for (WebElement e : list) {
                String date=e.getText();
                if(date.equalsIgnoreCase("2")){
                    e.click();
                }
            }
        }*/
        //String expectedDate = "31-Oct-2022";
        /*String [] Date=expectedDate.split("-");
        String expDate=Date[0];
        String expMonthYear=Date[1]+Date[2];*/
        //String expDate=expectedDate.split("-")[0];
        //String expMonthYear=expectedDate.split("-")[1]+expectedDate.split("-")[2];
       /* String expMonthYear="Oct 2022";
        String monthYear=driver.findElement(By.xpath("//td[@class='monthTitle']")).getText().trim();

        while(!monthYear.equalsIgnoreCase(expMonthYear)){
            WebElement next=driver.findElement(By.xpath("//td[@class='next']"));
            next.click();
        }
        List<WebElement> dates=driver.findElements(By.xpath("//td[@class='wd day']"));
        for(WebElement e:dates){
            if(e.getText().trim().equalsIgnoreCase(expDate)){
                e.click();
                break;
            }
        }*/
        String expMonthYear="Dec 2025";
        String expDay="31";
        while (true){
            String monthYear=driver.findElement(By.xpath("//td[@class='monthTitle']")).getText().trim();
            if(monthYear.equalsIgnoreCase(expMonthYear)){
                break;
            }else{
                WebElement next=driver.findElement(By.xpath("//td[@class='next']"));
                next.click();
            }
        }
        driver.findElement(By.xpath("//tbody//td[contains(text(),"+expDay+")]")).click();
    }
}

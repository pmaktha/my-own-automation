package com.selenium;

import com.Utils.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class DailyTasks extends Utils {

    WebDriver driver;

    @Test
    public void secretServer() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver.get("https://pcs.deloitteresources.com/PCS/Login.aspx");
        driver.navigate().to("https://pcs.deloitteresources.com/PCS/Login.aspx");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//input[@class='LoginTextBox thy-username']")).sendKeys("mprashanthreddy@deloitte.com");
        driver.findElement(By.xpath("//input[@class='LoginTextBox thy-password']")).sendKeys(decodePassword("RWFnbGVANDk1MQ=="));
        Select select = new Select(driver.findElement(By.xpath("//select[@id='LoginNewUiUserControl1_DomainDropDownList']")));
        select.selectByVisibleText("us.deloitte.com");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//button[@type='submit']")).isDisplayed());
        //used Joptionspane to enter MFA code
        String mfa = JOptionPane.showInputDialog("Enter your MFA here:");
        driver.findElement(By.id("LoginNewUiUserControl1_NextTokenCodeTextBox")).sendKeys(mfa);
        driver.findElement(By.xpath("//button[@value='Log In']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("atrame.deloitte.com")));
        link.click();
        try {
            WebElement enterComment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Enter Comment']")));
            enterComment.click();
            driver.findElement(By.xpath("//textarea[@id='thyinput-ReasonforView']")).sendKeys("Log in");
            Thread.sleep(1000);
            WebElement rightOfCancelButton =
                    driver.findElement(RelativeLocator.
                            with(By.xpath("//button[@id='new-request-continue-button']")).
                            toRightOf(driver.findElement(By.xpath
                                    ("//button[normalize-space()='Cancel']"))));
            rightOfCancelButton.click();
        } catch (TimeoutException e) {
            System.out.println(e.getMessage());
        } finally {
            WebElement password = driver.findElement(By.xpath("//*[name()='path' and contains(@d,'M12 6c3.79')]"));
            Actions actions = new Actions(driver);
            //actions.scrollByAmount(0,2000).perform();
            actions.moveToElement(password).click().perform();
            Thread.sleep(2000);
            WebElement text = driver.findElement(By.xpath("//div[@class='mat-tooltip-trigger thy-form-value-view thy-monospace ng-star-inserted'  and not (contains(@span,'thy-password-show-hide-non-edit'))]"));
            highlightElement(text, driver);
            Thread.sleep(1000);
            System.out.println("Copy the below remote desktop password:\n" + text.getText());
            driver.findElement(By.xpath("//div[@class='circle-text initials-text' and normalize-space()='PrM']")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Log Out']"))).click();
            WebElement logIn=driver.findElement(By.linkText("Log In"));
            String logInText=logIn.getText();
            Assert.assertEquals(logInText,"Log In","Expected Text is not Matching");
            Thread.sleep(1000);
            driver.quit();
        }
    }

    @Test(enabled = false)
    public void multipleWindows(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.navigate().to("https://the-internet.herokuapp.com/");
        driver.findElement(By.linkText("Multiple Windows")).click();
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.navigate().to("https://the-internet.herokuapp.com/windows/new");
        driver.switchTo().newWindow(WindowType.TAB);
    }
    @Test(description = "Scroll the bar horizontally and vertically")
    public void scrollElements(){

    }
    @Test
    public void google() throws InterruptedException {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        int totalFrames=driver.findElements(By.tagName("iframe")).size();
        System.out.println(totalFrames);
        driver.switchTo().frame("callout");
        Thread.sleep(2000);
        driver.findElement(RelativeLocator.with(By.xpath("//div[@class='QlyBfb']//following::a")).toLeftOf(By.tagName("button"))).click();
        driver.switchTo().defaultContent();
        Assert.assertTrue(driver.getTitle().contains("Google"),"Title not matching");


    }

}


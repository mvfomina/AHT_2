package com.amazon.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginWithInvalidPassword {

    static WebDriver driver;

    @Test
    public void loginTest() throws InterruptedException {
        //1. go to amazon.com
        System.setProperty("webdriver.chrome.driver", "drivers/windows/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://www.amazon.com/");
        Thread.sleep(2000);
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");
        //2. closing Change address popup
        driver.findElement(By.xpath("//*[@data-action-type=\"DISMISS\"]")).click();
        Thread.sleep(2000);
        //3. click on login
        driver.findElement(By.xpath("//*[contains(text(),'Hello, Sign in')]")).click();
        pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Amazon Sign-In");
        //4. insert login
        driver.findElement(new ById("ap_email")).sendKeys("AHT.test0@gmail.com");
        //5. click on continue
        driver.findElement(new ById("continue")).click();
        //6. insert password
        driver.findElement(new ById("ap_password")).sendKeys("Asdf123451212!");
        //7. click on sign-in
        driver.findElement(new ById("signInSubmit")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'To better protect your account, please re-enter your password and then enter the characters as they are shown in the image below.')]")).isDisplayed());
        driver.quit();
    }

}
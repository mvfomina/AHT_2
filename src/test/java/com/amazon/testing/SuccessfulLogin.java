package com.amazon.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SuccessfulLogin {

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
        driver.findElement(new ById("ap_password")).sendKeys("Asdf12345!");
        //7. click on sign-in
        driver.findElement(new ById("signInSubmit")).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'To continue, approve the notification sent to:')]")).isDisplayed());
   //     Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'AHT.test0')]")).isDisplayed());
        driver.quit();
    }

}
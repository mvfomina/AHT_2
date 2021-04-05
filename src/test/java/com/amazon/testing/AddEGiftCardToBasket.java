package com.amazon.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddEGiftCardToBasket{

    static WebDriver driver;

    @Test
    public void addGiftCardToBasket() throws InterruptedException {
        //1. go to amazon.com
        System.setProperty("webdriver.chrome.driver", "drivers/windows/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://www.amazon.com/");
        Thread.sleep(1000);
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");
        //2. closing Change address popup
        driver.findElement(By.xpath("//*[@data-action-type=\"DISMISS\"]")).click();
        //3. go to Gift Cards
        driver.findElement(By.xpath("//*[contains(text(),'Gift Cards')]")).click();
        pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Amazon.com Gift Cards");
        //4. click on eGift
        driver.findElement(By.xpath("//*[@alt=\"eGift\"]")).click();
        pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Amazon.com: Amazon eGift Card - Birthday Cupcakes: Gift Cards");
        //5. inserting email
        driver.findElement(new ById("gc-order-form-recipients")).sendKeys("mvfomina@ukr.net");
        //6. add eGift to the basket
        driver.findElement(new ById("gc-buy-box-atc")).click();
        Thread.sleep(3000);
        pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Amazon.com Shopping Cart");
        //7. opening basket
        driver.findElement(new ById("nav-cart-count-container")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'mvfomina@ukr.net')]")).isDisplayed());
        driver.quit();
    }

}
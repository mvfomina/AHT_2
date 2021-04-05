package com.amazon.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchWithNoResultsFound {

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
        //3. select Baby in departments
        driver.findElement(By.xpath("//*[@id=\"searchDropdownBox\"]")).click();
        driver.findElement(new ById("searchDropdownBox")).sendKeys("Baby");;
        driver.findElement(new ById("twotabsearchtextbox")).sendKeys("asfrs dsgdf gdfg");;
        //4. click on search button
        driver.findElement(new ById("nav-search-submit-button")).click();
        pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Amazon.com : asfrs dsgdf gdfg");
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'No results for')]")).isDisplayed());
        driver.quit();
    }

}
package com.example.newproject;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class controlGroup {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "c:\\webdriver\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        Actions actions = new Actions(driver);
        driver.get("https://jqueryui.com/controlgroup/");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,400)");
        WebElement autocompletePage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(autocompletePage);
        WebElement compactCar=driver.findElement(By.xpath("//*[@id=\"car-type-button\"]/span[1]"));
        waitForElement(driver,compactCar);
        compactCar.click();
        WebElement suv=driver.findElement(By.xpath("//*[@id=\"ui-id-4\"]"));
        waitForElement(driver,suv);
        suv.click();
        WebElement standard=driver.findElement(By.xpath("/html/body/div[1]/fieldset[1]/div/label[1]/span[1]"));
        standard.click();
        WebElement insurance=driver.findElement(By.xpath("/html/body/div[1]/fieldset[1]/div/label[3]/span[1]"));
        insurance.click();
        WebElement numbersOfCars=driver.findElement(By.xpath("/html/body/div[1]/fieldset[1]/div/span[2]/a[1]/span[1]"));
        numbersOfCars.click();
        WebElement bookButton=driver.findElement(By.xpath("/html/body/div[1]/fieldset[1]/div/button"));
        bookButton.click();


    }

    public static void waitForElement(WebDriver driver, WebElement element)
    {
        WebDriverWait webDriverWait=new WebDriverWait(driver,30);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }
}
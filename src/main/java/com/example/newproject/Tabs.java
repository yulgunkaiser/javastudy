package com.example.newproject;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Tabs {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "c:\\webdriver\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        Actions actions = new Actions(driver);


        //dialog

        driver.get("https://jqueryui.com/");
        driver.get("https://jqueryui.com/tabs/");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,300)");
        WebElement tabsPage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(tabsPage);
        WebElement tabs2=driver.findElement(By.linkText("Proin dolor"));
        tabs2.click();
        WebElement tabs3=driver.findElement(By.linkText("Aenean lacinia"));
        tabs3.click();
        WebElement tabs=driver.findElement(By.linkText("Nunc tincidunt"));
        tabs.click();

    }


    public static void waitForElement(WebDriver driver, WebElement element)
    {
        WebDriverWait webDriverWait=new WebDriverWait(driver,30);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }
    //sleep
    public static void sleep (int seconds) {
        try { Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}}
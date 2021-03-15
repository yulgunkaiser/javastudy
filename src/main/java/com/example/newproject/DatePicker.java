package com.example.newproject;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DatePicker {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "c:\\webdriver\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        Actions actions = new Actions(driver);
        driver.get("https://jqueryui.com/datepicker/");
        WebElement controlGroupPage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(controlGroupPage);
        WebElement dataBox=driver.findElement(By.id("datepicker"));
        dataBox.click();
        WebElement prevMonthButton=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[1]/span"));
        prevMonthButton.click();
        WebElement day10=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[4]/a"));
        day10.click();


    }

    public static void waitForElement(WebDriver driver, WebElement element)
    {
        WebDriverWait webDriverWait=new WebDriverWait(driver,30);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }
}
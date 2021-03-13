package com.example.newproject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class autocomplete {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","c:\\webdriver\\chromedriver.exe");
        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriver driver=new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        Actions actions=new Actions(driver);
        driver.get("https://jqueryui.com/autocomplete/");
        WebElement autocompletePage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(autocompletePage);
        WebElement tagInputBox=driver.findElement(By.id("tags"));
        tagInputBox.sendKeys("b");
        //JavascriptExecutor js = (JavascriptExecutor)driver;
        //js.executeScript();




    }
}

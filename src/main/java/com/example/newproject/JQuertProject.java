package com.example.newproject;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class JQuertProject {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","c:\\webdriver\\chromedriver.exe");
        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriver driver=new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.get("https://jqueryui.com");

        //draggable
        WebElement draggableLink=driver.findElement(By.linkText("Draggable"));
        draggableLink.click();
        WebElement draggableIframe=driver.findElement(By.tagName("iframe"));
        waitForElement(driver,draggableIframe);
        driver.switchTo().frame(draggableIframe);
        WebElement draggableObject=driver.findElement(By.cssSelector(".ui-widget-content.ui-draggable.ui-draggable-handle"));
        Actions actions=new Actions(driver);
        actions.clickAndHold(draggableObject).moveByOffset(60,0).release();
        actions.build().perform();

        //droppable
        driver.get("https://jqueryui.com/droppable/");
        //WebElement droppableElement=driver.findElement(By.linkText("Droppable"));
        //droppableElement.click();
        WebElement droppableiframe=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(droppableiframe);
        WebElement draggable=driver.findElement(By.id("draggable"));
        WebElement droppable=driver.findElement(By.id("droppable"));
        actions.dragAndDrop(draggable,droppable).build().perform();

        //resize]
        driver.get("https://jqueryui.com/resizable/");
        //WebElement resizableLink=driver.findElement(By.linkText("Resizable"));
        //resizableLink.click();
        WebElement resizableiframe2=driver.findElement(By.tagName("iframe"));
        waitForElement(driver,resizableiframe2);
        driver.switchTo().frame(resizableiframe2);
        WebElement resizableElement=driver.findElement(By.cssSelector(".ui-resizable-handle.ui-resizable-se.ui-icon.ui-icon-gripsmall-diagonal-se"));
        Point beforeResize=resizableElement.getLocation();
        System.out.println(beforeResize.x);

        Actions actionsResize=new Actions(driver);
        actionsResize.clickAndHold(resizableElement).moveByOffset(20,30).release().build().perform();
        Point afterResize=resizableElement.getLocation();
        System.out.println(afterResize.x);
        System.out.println("Before resize:"+beforeResize.x+"/"+"After reize: "+afterResize.x);
        driver.close();
        driver.quit();

    }
    public static void waitForElement(WebDriver driver, WebElement element)
    {
        WebDriverWait webDriverWait=new WebDriverWait(driver,30);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

}

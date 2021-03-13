package com.example.newproject;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class JQuertTest {
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
        waitForElement(driver,draggableLink);
        draggableLink.click();
        WebElement draggablePage=driver.findElement(By.tagName("iframe"));
        waitForElement(driver,draggablePage);
        driver.switchTo().frame(draggablePage);
        WebElement draggableObject=driver.findElement(By.cssSelector(".ui-widget-content.ui-draggable.ui-draggable-handle"));
        Actions actions=new Actions(driver);
        actions.clickAndHold(draggableObject).moveByOffset(60,0).release();
        actions.build().perform();

        //droppable
        driver.get("https://jqueryui.com/droppable/");
        //WebElement droppableElement=driver.findElement(By.linkText("Droppable"));
        //droppableElement.click();
        WebElement droppablePage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(droppablePage);
        WebElement draggable=driver.findElement(By.id("draggable"));
        WebElement droppable=driver.findElement(By.id("droppable"));
        actions.dragAndDrop(draggable,droppable).build().perform();

        //resize]
        driver.get("https://jqueryui.com/resizable/");
        //WebElement resizableLink=driver.findElement(By.linkText("Resizable"));
        //resizableLink.click();
        WebElement resizablePage=driver.findElement(By.tagName("iframe"));
        waitForElement(driver,resizablePage);
        driver.switchTo().frame(resizablePage);
        WebElement resizableElement=driver.findElement(By.cssSelector(".ui-resizable-handle.ui-resizable-se.ui-icon.ui-icon-gripsmall-diagonal-se"));
        Point beforeResize=resizableElement.getLocation();
        System.out.println(beforeResize.x);

        Actions actionsResize=new Actions(driver);
        actionsResize.clickAndHold(resizableElement).moveByOffset(20,30).release().build().perform();
        Point afterResize=resizableElement.getLocation();
        System.out.println(afterResize.x);
        System.out.println("Before resize:"+beforeResize.x+"/"+"After reize: "+afterResize.x);

        //selectable(single item)
        driver.get("https://jqueryui.com/selectable/");
        WebElement selectablePage=driver.findElement(By.tagName("iframe"));
        waitForElement(driver,selectablePage);
        driver.switchTo().frame(selectablePage);
        WebElement item1=driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[1]"));
        waitForElement(driver,item1);
        item1.click();
        //select multiple items
        WebElement item2=driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[2]"));
        WebElement item3=driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[3]"));
        waitForElement(driver,item3);
        actions.dragAndDrop(item2,item3).release();
        actions.build().perform();

        //sortable
        driver.get("https://jqueryui.com/sortable/");
        WebElement sortablePage=driver.findElement(By.tagName("iframe"));

        driver.switchTo().frame(sortablePage);
        WebElement sortableItem1=driver.findElement(By.xpath("//*[@id=\"sortable\"]/li[1]"));
        WebElement sortablrItem2=driver.findElement(By.xpath("//*[@id=\"sortable\"]/li[2]"));

        actions.clickAndHold(sortableItem1).moveToElement(sortablrItem2,0,50).release();
        actions.build().perform();

        //accordion
        driver.get("https://jqueryui.com/accordion/");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,500)");
        WebElement accordionPage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(accordionPage);
        WebElement section4 = driver.findElement(By.cssSelector("#ui-id-7"));
        waitForElement(driver,section4);
        actions.moveToElement(section4).release();
        actions.build().perform();
        section4.click();

        //autocomplete




        //button
        driver.get("https://jqueryui.com/button/");
        WebElement autocompletePage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(autocompletePage);
        WebElement widgetButton=driver.findElement(By.xpath("/html/body/div/button"));
        waitForElement(driver,widgetButton);
        widgetButton.click();
        WebElement submitButton=driver.findElement(By.cssSelector("body > div > input"));
        waitForElement(driver,submitButton);
        submitButton.click();
        WebElement widgetAnchor=driver.findElement(By.linkText("An anchor"));
        waitForElement(driver,widgetAnchor);
        widgetAnchor.click();
        WebElement cssButton=driver.findElement(By.xpath("/html/body/button"));
        waitForElement(driver,cssButton);
        cssButton.click();
        WebElement cssSubmit=driver.findElement(By.cssSelector("body > input"));
        waitForElement(driver,cssSubmit);
        cssSubmit.click();
        WebElement cssAnchor=driver.findElement(By.cssSelector("body > a"));
        waitForElement(driver,cssAnchor);
        cssAnchor.click();




        driver.close();
        driver.quit();

    }
    public static void waitForElement(WebDriver driver, WebElement element)
    {
        WebDriverWait webDriverWait=new WebDriverWait(driver,30);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

}

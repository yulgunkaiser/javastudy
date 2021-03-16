package com.example.jquryProject;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class JQueryTest {
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
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,300)");
        WebElement draggablePage=driver.findElement(By.tagName("iframe"));
        waitForElement(driver,draggablePage);
        driver.switchTo().frame(draggablePage);
        sleep(1);
        WebElement draggableObject=driver.findElement(By.cssSelector(".ui-widget-content.ui-draggable.ui-draggable-handle"));
        Actions actions=new Actions(driver);
        Point beforeDrag=draggableObject.getLocation();
        int beforeDragToX=beforeDrag.getX();
        actions.clickAndHold(draggableObject).moveByOffset(100,0).release();
        actions.build().perform();
        Point afterLocation=draggableObject.getLocation();
        int afterDragToX=afterLocation.getX();
        if (afterDragToX>beforeDragToX)
        {
            System.out.println("Test, passed, object has dragged to another location ");
        }
        else
            System.out.println("Test failed");
        sleep(2);


        //droppable
        driver.get("https://jqueryui.com/droppable/");
        jse.executeScript("window.scrollBy(0,300)");
        WebElement droppablePage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(droppablePage);
        WebElement draggable=driver.findElement(By.id("draggable"));
        WebElement droppable=driver.findElement(By.id("droppable"));
        actions.dragAndDrop(draggable,droppable).build().perform();
        WebElement confirmationMessage=driver.findElement(By.xpath("//*[@id=\"droppable\"]/p"));
        if (confirmationMessage.isDisplayed())
        {
            System.out.println("Test passed, object dropped into the target");
        }
        else
            System.out.println("test failed");
        sleep(2);


        //resize]
        driver.get("https://jqueryui.com/resizable/");
        jse.executeScript("window.scrollBy(0,300)");
        WebElement resizablePage=driver.findElement(By.tagName("iframe"));
        waitForElement(driver,resizablePage);
        driver.switchTo().frame(resizablePage);
        WebElement resizableElement=driver.findElement(By.cssSelector(".ui-resizable-handle.ui-resizable-se.ui-icon.ui-icon-gripsmall-diagonal-se"));
        Point beforeResize=resizableElement.getLocation();
        int beforeResizeY=beforeResize.getY();
        System.out.println(beforeResize.x);
        Actions actionsResize=new Actions(driver);
        actionsResize.clickAndHold(resizableElement).moveByOffset(50,30).release().build().perform();
        Point afterResize=resizableElement.getLocation();
        int afterResizeY=afterResize.getY();
        System.out.println(afterResize.x);
        System.out.println("Before resize:"+beforeResize.x+"/"+"After reize: "+afterResize.x);
        if (afterResizeY>beforeResizeY)
            System.out.println("Test passed, object resized");
        else
            System.out.println("Test failed");
        sleep(2);


        //selectable(single item)
        driver.get("https://jqueryui.com/selectable/");
        jse.executeScript("window.scrollBy(0,300)");
        WebElement selectablePage=driver.findElement(By.tagName("iframe"));
        waitForElement(driver,selectablePage);
        driver.switchTo().frame(selectablePage);
        WebElement item1=driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[1]"));
        waitForElement(driver,item1);
        item1.click();
        WebElement item1Selected=driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[1]"));
        if (item1Selected.isDisplayed())
            System.out.println("Test passed, item1 selected");
        else
            System.out.println("Test failed");
        sleep(1);
        //select multiple items
        WebElement item2=driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[2]"));
        WebElement item3=driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[3]"));
        waitForElement(driver,item3);
        actions.dragAndDrop(item2,item3).release();
        actions.build().perform();
        WebElement item2Selected=driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[2]"));
        WebElement item3Selected=driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[3]"));
        if (item2Selected.isDisplayed())
            if (item3Selected.isDisplayed())
                System.out.println("Test passed, multiple items selected");
            else
                System.out.println("Test failed");
        sleep(2);


        //sortable
        driver.get("https://jqueryui.com/sortable/");
        jse.executeScript("window.scrollBy(0,300)");
        WebElement sortablePage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(sortablePage);
        WebElement sortableItem1=driver.findElement(By.xpath("//*[@id=\"sortable\"]/li[1]"));
        Point beforeSort=sortableItem1.getLocation();
        int beforeSortToY=beforeSort.getY();
        WebElement sortableItem2=driver.findElement(By.xpath("//*[@id=\"sortable\"]/li[2]"));
        actions.clickAndHold(sortableItem1).moveToElement(sortableItem2,0,50).release();
        actions.build().perform();
        Point afterSort=sortableItem1.getLocation();
        int afterSortToY=afterSort.getY();
        if (afterSortToY>beforeSortToY)
            System.out.println("Test passed, items reordered");
        else
            System.out.println("Test failed");
        sleep(2);


        //accordion
        driver.get("https://jqueryui.com/accordion/");
        jse.executeScript("window.scrollBy(0,300)");
        WebElement accordionPage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(accordionPage);
        WebElement section4 = driver.findElement(By.cssSelector("#ui-id-7"));
        waitForElement(driver,section4);
        actions.moveToElement(section4).release();
        actions.build().perform();
        sleep(1);
        section4.click();
        WebElement content=driver.findElement(By.xpath("//*[@id=\"ui-id-8\"]"));
        if (content.isDisplayed())
            System.out.println("Test passed, content is displayed");
        else
            System.out.println("Test failed");
        sleep(2);


        //autocomplete
        driver.get("https://jqueryui.com/autocomplete/");
        jse.executeScript("window.scrollBy(0,300)");
        WebElement autocompletePage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(autocompletePage);
        WebElement tagBox=driver.findElement(By.xpath("//*[@id=\"tags\"]"));
        tagBox.sendKeys("b");
        sleep(2);
        WebElement option=driver.findElement(By.xpath("//ul/li/div[text()='BASIC']"));
        option.click();
        WebElement confirmation=driver.findElement(By.xpath("//*[@id=\"tags\"]"));
        if (confirmation.isDisplayed())
            System.out.println("Test passed, option typed in the tag ");
        else
            System.out.println("Test failed");


        //button
        driver.get("https://jqueryui.com/button/");
        jse.executeScript("window.scrollBy(0,300)");
        WebElement buttonPage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(buttonPage);
        WebElement widgetButton=driver.findElement(By.xpath("/html/body/div/button"));
        waitForElement(driver,widgetButton);
        widgetButton.click();
        sleep(1);
        WebElement submitButton=driver.findElement(By.cssSelector("body > div > input"));
        waitForElement(driver,submitButton);
        submitButton.click();
        sleep(1);
        WebElement widgetAnchor=driver.findElement(By.linkText("An anchor"));
        waitForElement(driver,widgetAnchor);
        widgetAnchor.click();
        sleep(1);
        WebElement cssButton=driver.findElement(By.xpath("/html/body/button"));
        waitForElement(driver,cssButton);
        cssButton.click();
        sleep(1);
        WebElement cssSubmit=driver.findElement(By.cssSelector("body > input"));
        waitForElement(driver,cssSubmit);
        cssSubmit.click();
        sleep(1);
        WebElement cssAnchor=driver.findElement(By.cssSelector("body > a"));
        waitForElement(driver,cssAnchor);
        cssAnchor.click();
        if (cssAnchor.isSelected())
            System.out.println("Test passed, button clicked");
        else
            System.out.println("Test failed");
        sleep(2);



        //radioButton
        driver.get("https://jqueryui.com/checkboxradio/");
        jse.executeScript("window.scrollBy(0,300)");
        WebElement radioButtonPage = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(radioButtonPage);
        WebElement newYorkButton=driver.findElement(By.xpath("/html/body/div/fieldset[1]/label[1]/span[1]"));
        waitForElement(driver,newYorkButton);
        newYorkButton.click();
        WebElement ratingButton=driver.findElement(By.xpath("/html/body/div/fieldset[2]/label[2]/span[1]"));
        waitForElement(driver,ratingButton);
        ratingButton.click();
        WebElement bedType=driver.findElement(By.xpath("/html/body/div/fieldset[3]/label[3]/span[1]"));
        waitForElement(driver,bedType);
        bedType.click();
        WebElement newYorkButtonChecked=driver.findElement(By.xpath("/html/body/div/fieldset[1]/label[1]"));
        WebElement ratingChecked=driver.findElement(By.xpath("/html/body/div/fieldset[2]/label[2]"));
        WebElement bedTypeChecked=driver.findElement(By.xpath("/html/body/div/fieldset[3]/label[3]"));
        if (newYorkButtonChecked.isDisplayed())
            if (ratingChecked.isDisplayed())
                if (bedTypeChecked.isDisplayed())
                    System.out.println("Test passed, city, rating, bed type checked");
                else
                    System.out.println("Test failed");
        sleep(1);



        //control group
        driver.get("https://jqueryui.com/controlgroup/");
        jse.executeScript("window.scrollBy(0,400)");
        WebElement controlGroupPage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(controlGroupPage);
        WebElement compactCar=driver.findElement(By.xpath("//*[@id=\"car-type-button\"]/span[1]"));
        waitForElement(driver,compactCar);
        compactCar.click();
        sleep(1);
        WebElement suv=driver.findElement(By.xpath("//*[@id=\"ui-id-4\"]"));
        waitForElement(driver,suv);
        suv.click();
        sleep(1);
        WebElement standard=driver.findElement(By.xpath("/html/body/div[1]/fieldset[1]/div/label[1]/span[1]"));
        standard.click();
        WebElement insurance=driver.findElement(By.xpath("/html/body/div[1]/fieldset[1]/div/label[3]/span[1]"));
        insurance.click();
        sleep(1);
        WebElement numbersOfCars=driver.findElement(By.xpath("/html/body/div[1]/fieldset[1]/div/span[2]/a[1]/span[1]"));
        numbersOfCars.click();
        sleep(1);
        WebElement bookButton=driver.findElement(By.xpath("/html/body/div[1]/fieldset[1]/div/button"));
        bookButton.click();
        WebElement suvSelected=driver.findElement(By.xpath("//*[@id=\"car-type-button\"]/span[2]"));
        WebElement standardChecked=driver.findElement(By.xpath("/html/body/div[1]/fieldset[1]/div/label[1]"));
        WebElement insuranceChecked=driver.findElement(By.xpath("/html/body/div[1]/fieldset[1]/div/label[3]"));
        WebElement numbersOfCarsChecked=driver.findElement(By.xpath("//*[@id=\"horizontal-spinner\"]"));
        if (suvSelected.isDisplayed())
            if (standardChecked.isDisplayed())
                if (insuranceChecked.isDisplayed())
                    if (numbersOfCarsChecked.isDisplayed())
                        System.out.println("Test passed, car type, standard, insurance, numbers of cards checked");
                    else
                        System.out.println("Test failed");
        sleep(1);


        //date picker
        driver.get("https://jqueryui.com/datepicker/");
        jse.executeScript("window.scrollBy(0,300)");
        WebElement datePickerPage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(datePickerPage);
        WebElement dataBox=driver.findElement(By.id("datepicker"));
        dataBox.click();
        WebElement prevMonthButton=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[1]/span"));
        prevMonthButton.click();
        WebElement day10=driver.findElement(By.linkText("10"));
        day10.click();
        WebElement monthSelected=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/span[1]"));
        if (monthSelected.isDisplayed())
            System.out.println("Test passed, date checked");
        else
            System.out.println("Test failed");
        sleep(1);


        //dialog
        driver.get("https://jqueryui.com/dialog/");
        jse.executeScript("window.scrollBy(0,300)");
        WebElement dialogPage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(dialogPage);
        WebElement dialogObject=driver.findElement(By.xpath("//*[@id=\"ui-id-1\"]"));
        actions.dragAndDropBy(dialogObject,110,110);
        WebElement resize=driver.findElement(By.xpath("/html/body/div/div[8]"));
        actions.clickAndHold(resize).moveByOffset(-200,-200).release();
        actions.build().perform();
        WebElement closeDialog=driver.findElement(By.xpath("/html/body/div/div[1]/button/span[1]"));
        closeDialog.click();
        WebElement contentClosed=driver.findElement(By.xpath("//*[@id=\"ui-id-1\"]"));
        if (!contentClosed.isDisplayed())
            System.out.println("Test passed, content closed");
        else
            System.out.println("Test failed");
        sleep(1);


        //menu
        driver.get("https://jqueryui.com/menu/");
        jse.executeScript("window.scrollBy(0,300)");
        WebElement menuPage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(menuPage);
        WebElement musicList=driver.findElement(By.id("ui-id-9"));
        actions.moveToElement(musicList).build().perform();
        sleep(2);
        WebElement rock=driver.findElement(By.xpath("//ul/li/div[text()='Rock']"));
        actions.moveToElement(rock).build().perform();
        sleep(2);
        WebElement alternative=driver.findElement(By.xpath("//ul/li/div[text()='Alternative']"));
        alternative.click();
        sleep(1);


        //Select Menu
        driver.get("https://jqueryui.com/selectmenu/");
        jse.executeScript("window.scrollBy(0,300)");
        WebElement selectMenuPage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(selectMenuPage);
        WebElement speedButton=driver.findElement(By.xpath("//*[@id=\"speed-button\"]/span[1]"));
        speedButton.click();
        WebElement fast=driver.findElement(By.xpath("//ul/li/div[text()='Fast']"));
        fast.click();
        sleep(2);
        WebElement fileButton=driver.findElement(By.xpath("//*[@id=\"files-button\"]/span[1]"));
        fileButton.click();
        WebElement jquryUI=driver.findElement(By.xpath("//ul/li/div[text()='ui.jQuery.js']"));
        jquryUI.click();
        WebElement numberButton=driver.findElement(By.xpath("//*[@id=\"number-button\"]/span[1]"));
        numberButton.click();
        WebElement number5=driver.findElement(By.xpath("//ul/li/div[text()='5']"));
        number5.click();
        sleep(2);
        WebElement titleButton=driver.findElement(By.xpath("//*[@id=\"salutation-button\"]/span[1]"));
        titleButton.click();
        WebElement title=driver.findElement(By.xpath("//ul/li/div[text()='Mr.']"));
        title.click();
        WebElement titleSelected=driver.findElement(By.xpath("//*[@id=\"salutation-button\"]/span[2]"));
        if (titleSelected.isDisplayed())
            System.out.println("Test passed, title selected");
        else
            System.out.println("Test failed");
        sleep(1);


        //slider
        driver.get("https://jqueryui.com/slider/");
        jse.executeScript("window.scrollBy(0,300)");
        WebElement sliderPage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(sliderPage);
        WebElement slider=driver.findElement(By.xpath("//*[@id=\"slider\"]/span"));
        Point beforeSlideLocation=slider.getLocation();
        int beforeSlideX=beforeSlideLocation.getX();
        actions.clickAndHold(slider).moveByOffset(200,0).moveByOffset(200,0).release().build().perform();
        Point afterSlideLocation=slider.getLocation();
        int afterSlideX=afterSlideLocation.getX();
        if (afterSlideX>beforeSlideX)
            System.out.println("Test passed, slider moved to the right");
        else
            System.out.println("Test failed");
        sleep(1);


        //tabs
        driver.get("https://jqueryui.com/tabs/");
        jse.executeScript("window.scrollBy(0,300)");
        WebElement tabsPage=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(tabsPage);
        WebElement tab2=driver.findElement(By.linkText("Proin dolor"));
        tab2.click();
        WebElement tab3=driver.findElement(By.linkText("Aenean lacinia"));
        tab3.click();
        WebElement tab1=driver.findElement(By.linkText("Nunc tincidunt"));
        tab1.click();
        WebElement tab1Clicked=driver.findElement(By.xpath("//*[@id=\"tabs-1\"]/p"));
                if (tab1Clicked.isDisplayed())
                    System.out.println("Test passed, all three tabs clicked, contents are displayed");
                else
                    System.out.println("Test failed");
        sleep(1);



        driver.close();
        driver.quit();

    }
    public static void waitForElement(WebDriver driver, WebElement element)
    {
        WebDriverWait webDriverWait=new WebDriverWait(driver,30);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }
    public static void sleep (int seconds) {
        try { Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}

}

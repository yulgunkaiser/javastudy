package com.example.newproject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;

public class droppable {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","c:\\webdriver\\chromedriver.exe");
        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriver driver=new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.get("https://jqueryui.com/selectable/");
        WebElement selectableIframe=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(selectableIframe);
        WebElement item1=driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[1]"));
        WebElement item3=driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[3]"));
        Actions actions=new Actions(driver);
        item1.click();
        actions.dragAndDrop(item1,item3).release();
        actions.build().perform();



    }
}

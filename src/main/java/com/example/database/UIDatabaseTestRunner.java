package com.example.database;

import com.example.pageobjectpattern.DashboardPage;
import com.example.pageobjectpattern.LoginPage;
import com.example.pageobjectpattern.ProductPage;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;

public class UIDatabaseTestRunner {
    WebDriver driver;
    //mysql server information
    Connection mysqlConnection;
    String mySqlDatabaseServer="148.72.106.125";
    String mySqlPort="3306";
    String mySqlUsername="readOnly";
    String mySqlPassword="readOnly123!";
    String mySqlDefaultDatabase="i4296639_cc1";
    @BeforeClass
    public void setup()
    {
        mysqlConnection=ConnectionManager.connectToDatabaseServer(mySqlDatabaseServer,mySqlPort,mySqlDefaultDatabase,
                mySqlUsername, mySqlPassword,ConnectionType.MYSQLSERVER);
        System.setProperty("webdriver.chrome.driver","c:\\webdriver\\chromedriver.exe");
        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver=new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://demo.cubecart.com/cc6/admin_5xArPd.php");
        LoginPage loginPage=new LoginPage(driver);
        loginPage.login("cubecart","cubecart");

    }
    @Test(groups = {"Add product"})
    public void addProductTest()
    {
        ProductPage productPage=new ProductPage(driver);
        String productName="Java"+System.currentTimeMillis();
        TestDataHolder.setProductName(productName);
        boolean isProductAdded=productPage.addNewProduct(productName,
                "New","Java102","5");
        Assert.assertTrue(isProductAdded);
    }

    @Test(dependsOnGroups = "Add product")
    public void verifyProductNameFromDatabase()
    {
        DataAccess dataAccess=new DataAccess();
        Assert.assertTrue(dataAccess.getProductNameFromCubeCart(TestDataHolder.getProductName(),mysqlConnection));
    }

    @AfterClass
    public void tearDown()
    {
        ConnectionManager.closeDatabaseConnection(mysqlConnection);
        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.logout();
        driver.close();
        driver.quit();
    }
}

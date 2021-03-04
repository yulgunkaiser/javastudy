package com.example.database;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.sql.Connection;
import java.util.Random;

public class DatabaseTestRunner {
    //sql server information
    Connection sqlConnection;
    String sqlDatabaseUrl ="unitedcodermssql01.cngiy67lj4rq.us-west-2.rds.amazonaws.com";
    String sqlDatabasePort ="1433";
    String sqlUserName ="student";
    String sqlPassword ="student123!";
    String sqlDefaultDatabase ="AdventureWorksStudent";
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
        sqlConnection =ConnectionManager.connectToDatabaseServer(sqlDatabaseUrl, sqlDatabasePort, sqlDefaultDatabase,
                sqlUserName, sqlPassword, ConnectionType.MSSQLSERVER);
        mysqlConnection=ConnectionManager.connectToDatabaseServer(mySqlDatabaseServer,mySqlPort,mySqlDefaultDatabase,
                mySqlUsername, mySqlPassword,ConnectionType.MYSQLSERVER);
    }
    @Test(description = "vrify department name from table")
    public void verifyDepartmentNameTest1()
    {
        DataAccess dataAccess=new DataAccess();
        Assert.assertTrue(dataAccess.getDepartmentName("Engineering", sqlConnection));
    }
    @Test(description = "vrify department name from table")
    public void verifyDepartmentNameTest2()
    {
        DataAccess dataAccess=new DataAccess();
        Assert.assertTrue(dataAccess.getDepartmentName("Engineering n", sqlConnection));
    }
    @Test(description = "Insert data into department table")
    public void insetDataIntoDepartmentTest()
    {
        Random random=new Random();
        int departmentID=random.nextInt(100);
        String departmentName="Automation-mili";
        Department testDepartment=new Department(departmentID,departmentName,"Engineering","");
        DataAccess dataAccess=new DataAccess();
        Assert.assertTrue(dataAccess.insetDataIntoDepartmentTable(testDepartment, sqlConnection));
    }
    @Test
    public void CubeCartProductCategoryVerificationTest()
    {
        DataAccess dataAccess=new DataAccess();
        Assert.assertTrue( dataAccess.getCategoryNameFromCubeCart("Category-Miliguli",mysqlConnection));
    }
    @AfterClass
    public void teardown()
    {
        ConnectionManager.closeDatabaseConnection(sqlConnection);
        ConnectionManager.closeDatabaseConnection(mysqlConnection);
    }
}

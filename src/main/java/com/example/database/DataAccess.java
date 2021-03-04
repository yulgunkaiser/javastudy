package com.example.database;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataAccess {
    //get department information
    public boolean getDepartmentName(String departmentName, Connection connection)
    {
        boolean isDepartmentExist=false;
        Statement statement=null;
        ResultSet resultSet=null;
        CachedRowSet cachedRowSet =null;
        try {
            cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sqlQuery=String.format("Select count(*) from HumanResources.Department where Name='%s'",departmentName);
        try {
            resultSet=statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (resultSet==null)
        {
            System.out.println("No records returned");
            return isDepartmentExist;
        }
        else {
            try {
                cachedRowSet.populate(resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("record found");
        }

        int count=0;
        while (true)
        {
            try {
                if (!cachedRowSet.next())
                    break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                count=cachedRowSet.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("total records:"+count);
        }
        if (count>0)
        {
            isDepartmentExist=true;
        }
        return isDepartmentExist;

    }
    public boolean insetDataIntoDepartmentTable(Department department, Connection connection)
    {
        Statement statement=null;
        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sqlInsertStatement=String.format("INSERT INTO HumanResources.Department values(%d,'%s','%s',GETDATE())",
                department.getDepartmentId(),department.getGroupName(),department.getGroupName());
        int affectedRow=0;
        try {
            affectedRow=statement.executeUpdate(sqlInsertStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (affectedRow>0)
            return true;
        else
            return false;

    }
    //this is for getting data form cubecart
    public boolean getCategoryNameFromCubeCart(String categoryName, Connection connection)
    {
        boolean isCategoryExist=false;
        Statement statement=null;
        ResultSet resultSet=null;
        CachedRowSet cachedRowSet =null;
        try {
            cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sqlQuery=String.format("Select count(*) from cc_CubeCart_category where cat_name_like '%s'",categoryName);
        try {
            resultSet=statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (resultSet==null)
        {
            System.out.println("No records returned");
            return isCategoryExist;
        }
        else {
            try {
                cachedRowSet.populate(resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("record found");
        }

        int count=0;
        while (true)
        {
            try {
                if (!cachedRowSet.next())
                    break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                count=cachedRowSet.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("total records:"+count);
        }
        if (count>0)
        {
            isCategoryExist=true;
        }
        return isCategoryExist;





    }
    public boolean getProductNameFromCubeCart(String categoryName, Connection connection)
     {
        boolean isProductExist=false;
        Statement statement=null;
        ResultSet resultSet=null;
        CachedRowSet cachedRowSet =null;
        try {
            cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sqlQuery=String.format("Select count(*) from cc_CubeCart_inventory where cat_name='%s';",categoryName);
        try {
            resultSet=statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (resultSet==null)
        {
            System.out.println("No records returned");
            return isProductExist;
        }
        else {
            try {
                cachedRowSet.populate(resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("record found");
        }

        int count=0;
        while (true)
        {
            try {
                if (!cachedRowSet.next())
                    break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                count=cachedRowSet.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("total records:"+count);
        }
        if (count>0)
        {
            isProductExist=true;
        }
        return isProductExist;





    }
}

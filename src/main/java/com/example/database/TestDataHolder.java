package com.example.database;

public class TestDataHolder {
    private static String productName;

    public static String getProductName() {
        return productName;
    }

    public static void setProductName(String productName) {
        TestDataHolder.productName = productName;
    }
}

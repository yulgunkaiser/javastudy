package com.example.database;

public class Department {
    private int departmentId;
    private String name;
    private String groupName;
    private String modifiedDate;

    public Department() {
    }

    public Department(int departmentId, String name, String groupName, String modifiedDate) {
        this.departmentId = departmentId;
        this.name = name;
        this.groupName = groupName;
        this.modifiedDate = modifiedDate;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }
}

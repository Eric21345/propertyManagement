package com.propertyManagement.util;

public class TaskParam {
    private String name;
    private String description;
    private int planNum;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = Integer.parseInt(planNum);
    }
}

package com.propertyManagement.pojo;

import java.math.BigInteger;

public class Project {
    private int id;
    private String name;
    private String description;
    private int planNum;
    private int currentNum;
    private BigInteger planMoney;
    private BigInteger currentMoney;
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPlanNum() {
        return planNum;
    }

    public void setPlanNum(int planNum) {
        this.planNum = planNum;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }

    public BigInteger getPlanMoney() {
        return planMoney;
    }

    public void setPlanMoney(BigInteger planMoney) {
        this.planMoney = planMoney;
    }

    public BigInteger getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(BigInteger currentMoney) {
        this.currentMoney = currentMoney;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

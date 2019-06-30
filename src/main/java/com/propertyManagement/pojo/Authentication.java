package com.propertyManagement.pojo;

public class Authentication {
    private int id;
    private String openId;
    private int staffId;
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffid(int staffId) {
        this.staffId = staffId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

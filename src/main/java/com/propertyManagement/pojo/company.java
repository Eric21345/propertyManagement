package com.propertyManagement.pojo;

public class company {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String boss;
    private String bossPhone;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getBossPhone() {
        return bossPhone;
    }

    public void setBossPhone(String bossPhone) {
        this.bossPhone = bossPhone;
    }
}

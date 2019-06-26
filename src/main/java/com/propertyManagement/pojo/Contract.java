package com.propertyManagement.pojo;

import java.math.BigInteger;
import java.sql.Date;

public class Contract {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String workType;
    private String workContent;
    private BigInteger totalMoney;
    private int frequency;
    private String state;
    private String remark;
    private int receive;
    private BigInteger receiveMoney;
    private BigInteger lastReceive;
    private int clientId;
    private int companyId;
    private int projectId;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public BigInteger getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigInteger totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getReceive() {
        return receive;
    }

    public void setReceive(int receive) {
        this.receive = receive;
    }

    public BigInteger getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(BigInteger receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public BigInteger getLastReceive() {
        return lastReceive;
    }

    public void setLastReceive(BigInteger lastReceive) {
        this.lastReceive = lastReceive;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}

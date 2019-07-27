package com.propertyManagement.util;

import java.sql.Date;

public class TaskParam {
    private String name;
    private String description;
    private int planNum;
    private Date startDate;
    private Date endDate;

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

    public void setPlanNum(int planNum) {
        this.planNum = planNum;
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
}

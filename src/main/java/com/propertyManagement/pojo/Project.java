package com.propertyManagement.pojo;

import java.sql.Date;

public class Project {
    private int id;
    private String project_name;
    private String description;
    private int plan_num;
    private int participate_num;
    private long plan_money;
    private long current_expend;
    private Date start_date;
    private Date end_date;
    private String status;
    private long contract_id;

    public long getContract_id() {
        return contract_id;
    }

    public void setContract_id(long contract_id) {
        this.contract_id = contract_id;
    }

    public long getCurrent_expend() {
        return current_expend;
    }

    public void setCurrent_expend(long current_expend) {
        this.current_expend = current_expend;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPlan_money() {
        return plan_money;
    }

    public void setPlan_money(long plan_money) {
        this.plan_money = plan_money;
    }

    public int getParticipate_num() {
        return participate_num;
    }

    public void setParticipate_num(int participate_num) {
        this.participate_num = participate_num;
    }

    public int getPlan_num() {
        return plan_num;
    }

    public void setPlan_num(int plan_num) {
        this.plan_num = plan_num;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

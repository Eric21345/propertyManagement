package com.propertyManagement.pojo;

import java.sql.Date;

public class Contract {
    private int id;
    private String company_name;
    private int client_id;
    private Date start_date;
    private Date end_date;
    private String work_type;
    private String work_content;
    private long cost;
    private String frequency;
    private String status;
    private String remark;
    private int receive;
    private long receive_money;
    private Date last_receive;

    public String getCompany_name() {
        return company_name;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public long getReceive_money() {
        return receive_money;
    }

    public void setReceive_money(long receive_money) {
        this.receive_money = receive_money;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }


    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLast_receive() {
        return last_receive;
    }

    public void setLast_receive(Date last_receive) {
        this.last_receive = last_receive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getReceive() {
        return receive;
    }

    public void setReceive(int receive) {
        this.receive = receive;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWork_content() {
        return work_content;
    }

    public void setWork_content(String work_content) {
        this.work_content = work_content;
    }

    public String getWork_type() {
        return work_type;
    }

    public void setWork_type(String work_type) {
        this.work_type = work_type;
    }
}

package com.propertyManagement.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class EUseApply {
    private int id;
    private int applicantId;
    private int equipmentId;
    private Date applyDate;
    private int handleId;
    private Date handleDate;
    private int result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "Asia/Shanghai")
    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public int getHandleId() {
        return handleId;
    }

    public void setHandleId(int handleId) {
        this.handleId = handleId;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "Asia/Shanghai")
    public Date getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(Date handleDate) {
        this.handleDate = handleDate;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}

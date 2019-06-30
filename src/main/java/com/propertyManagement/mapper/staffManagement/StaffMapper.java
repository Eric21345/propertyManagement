package com.propertyManagement.mapper.staffManagement;

import com.propertyManagement.pojo.Staff;

import java.util.Date;
import java.util.List;

public interface StaffMapper {
    List<Staff> getMonitorList();
    List<Staff> getStaffList();
    void deleteStaff(int id);
    Staff getStaffInfoById(int id);
    Staff getStaffByOpenId(String openId);
    int addApplyStaff(Staff staff);
    void updateStaffTypeById(int staffId);

}

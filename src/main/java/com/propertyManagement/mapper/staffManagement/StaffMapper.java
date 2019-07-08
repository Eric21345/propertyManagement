package com.propertyManagement.mapper.staffManagement;

import com.propertyManagement.pojo.Staff;
import com.propertyManagement.pojo.Task;

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
    List<Staff> getStaffListByCompanyId(int companyId);
    List<Staff> getStaffListByNameStr(String nameStr);
    void deleteTaskByStaffIdAndTaskId(int taskId);
    List<Staff> getStaffInfoInAuthentication();
}

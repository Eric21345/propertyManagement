package com.propertyManagement.service.staffManagement;

import com.propertyManagement.mapper.staffManagement.StaffMapper;
import com.propertyManagement.pojo.ParameterList;
import com.propertyManagement.pojo.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StaffService {
    @Autowired
    private StaffMapper staffMapper;

    public List<Staff> getMonitorList(){
        return staffMapper.getMonitorList();
    }

    public Staff getStaffInfoById(int id){
        return staffMapper.getStaffInfoById(id);
    }

    public void deleteStaff(int id){
        staffMapper.deleteStaff(id);
    }

    public Staff getStaffByOpenId(String openId){
        return staffMapper.getStaffByOpenId(openId);
    }

    public int addApplyStaff(Staff staff){
        return staffMapper.addApplyStaff(staff);
    }

    public void updateStaffTypeById(int staffId){
        staffMapper.updateStaffTypeById(staffId);
    }

    public List<Staff> getStaffListByCompanyId(ParameterList parameterList){
        return staffMapper.getStaffListByCompanyId(parameterList);
    }

    public List<Staff> getStaffListByNameStr(String nameStr){ return staffMapper.getStaffListByNameStr(nameStr); }

    public List<Staff> getStaffInfoInAuthentication(){return staffMapper.getStaffInfoInAuthentication();}

    public void addStaff(Staff staff){
        staffMapper.addStaff(staff);
    }
}

package com.propertyManagement.service.staffManagement;

import com.propertyManagement.mapper.staffManagement.StaffMapper;
import com.propertyManagement.pojo.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    @Autowired
    private StaffMapper staffMapper;

    public List<Staff> getMonitorList(){
        return staffMapper.getMonitorList();
    }

    public List<Staff> getStaffList(){
        return staffMapper.getStaffList();
    }

    public Staff getStaffInfoById(int id){
        return staffMapper.getStaffInfoById(id);
    }

    public void deleteStaff(int id){
        staffMapper.deleteStaff(id);
    }
}

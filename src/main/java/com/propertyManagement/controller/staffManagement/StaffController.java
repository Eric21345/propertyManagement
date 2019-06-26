package com.propertyManagement.controller.staffManagement;

import com.propertyManagement.pojo.Staff;
import com.propertyManagement.service.staffManagement.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StaffController {
    @Autowired
    private StaffService staffService;

    //获取员工列表
    @RequestMapping("getStaffList")
    @ResponseBody
    public Map getStaffList(){
        Map map = new HashMap();
        map.put("status",1);
        List<Staff> staffList = staffService.getStaffList();
        map.put("staffList",staffList);
        return map;

    }

    //依据员工id获得员工详情
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getStaffInfoById")
    public Map getStaffInfoById(@RequestParam("id") int id){
        Staff staff = staffService.getStaffInfoById(id);
        Map map = new HashMap();
        map.put("status", 1);
        map.put("staff", staff);
        return map;
    }

    //删除员工
    @ResponseBody
    @RequestMapping("deleteStaff")
    public Map deleteStaff(@RequestParam("id") int id){
        staffService.deleteStaff(id);
        Map map = new HashMap();
        map.put("status",1);
        return map;
    }


}

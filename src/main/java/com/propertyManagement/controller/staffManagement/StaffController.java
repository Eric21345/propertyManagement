package com.propertyManagement.controller.staffManagement;

import com.propertyManagement.pojo.Authentication;
import com.propertyManagement.pojo.Staff;
import com.propertyManagement.service.Login.AuthenticationService;
import com.propertyManagement.service.staffManagement.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StaffController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private AuthenticationService authenticationService;

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
        Staff staffInfo = staffService.getStaffInfoById(id);
        Map map = new HashMap();
        map.put("status", 1);
        map.put("staffInfo", staffInfo);
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

    //管理人员处理员工认证申请
    @ResponseBody
    @RequestMapping("handleAuthentication")
    public Map addAuthentication(@RequestParam("authenticationId") int authenticationId,
                                 @RequestParam("handler") Staff handler){
        //需要对两张表进行操作
        //staff表更新type字段信息，authentication表更新处理信息
        //获取被处理员工的id并更新staff表
        staffService.updateStaffTypeById(authenticationService.getStaffIdByAuthenticationId(authenticationId));
        //更新authentication表
        authenticationService.updateAuthentication(handler.getId(), new Date(), 1, authenticationId);
        Map map = new HashMap();
        map.put("status", 1);
        return map;
    }

    //管理员显示新员工认证申请列表
    @SuppressWarnings("unchecked")
    @RequestMapping("handleNewStaffApply")
    public Map listNewStaff(@RequestParam("staffId") int id){
        Map map = new HashMap();
        List<Authentication> newStaffList = authenticationService.getAuthenticationList();
        map.put("status",1);
        map.put("newStaffList",newStaffList);
        return map;
    }

    //获取公司所有员工
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getStaffListByCompanyId")
    public Map getStaffListByCompanyId(@RequestParam("companyId") int companyId){
        Map map = new HashMap();
        List<Staff> staffList = staffService.getStaffListByCompanyId(companyId);
        map.put("status", 1);
        map.put("staffList", staffList);
        return map;
    }

    //依据员工姓名模糊查询员工
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getStaffListByNameStr")
    public Map getStaffListByNameStr(@RequestParam("nameStr") String nameStr){
        Map map = new HashMap();
        List<Staff> staffList = staffService.getStaffListByNameStr(nameStr);
        map.put("status", 1);
        map.put("staffList", staffList);
        return map;
    }


}

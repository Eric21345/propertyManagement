package com.propertyManagement.service.Login;

import com.propertyManagement.mapper.login.LoginMapper;
import com.propertyManagement.pojo.ManageStaff;
import com.propertyManagement.pojo.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;

    public List<ManageStaff> listManageStaff(){return loginMapper.list();}
    public void addStaff(Staff staff){loginMapper.addStaff(staff);}

}

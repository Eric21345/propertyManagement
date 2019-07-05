package com.propertyManagement.service.Login;

import com.propertyManagement.mapper.login.AuthenticationMapper;
import com.propertyManagement.pojo.Authentication;
import com.propertyManagement.pojo.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationMapper authenticationMapper;

    public Authentication getAuthenticationByOpenId(String openId){
        return authenticationMapper.getAuthenticationByOpenId(openId);
    }

    public List<Company> getCompanyList(){
        return authenticationMapper.getCompanyList();
    }

    public void addAuthentication(String openId, int staffId, int state){
        authenticationMapper.addAuthentication(openId, staffId, state);
    }

    public void updateAuthentication(int handlerId, Date handleDate, int state, int authenticationId){
        authenticationMapper.updateAuthentication(handlerId, handleDate, state, authenticationId);
    }

    public int getStaffIdByAuthenticationId(int authenticationId){
        return authenticationMapper.getStaffIdByAuthenticationId(authenticationId);
    }

    public List<Authentication> getAuthenticationList(){return authenticationMapper.getAuthenticationList();}

}

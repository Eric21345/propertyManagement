package com.propertyManagement.mapper.login;

import com.propertyManagement.pojo.Authentication;
import com.propertyManagement.pojo.Company;

import java.util.Date;
import java.util.List;

public interface AuthenticationMapper {
    Authentication getAuthenticationByOpenId(String openId);
    List<Company> getCompanyList();
    void addAuthentication(String openId, int staffId, int state);
    void updateAuthentication(int handlerId, Date handleDate, int state, int authenticationId);
    int getStaffIdByAuthenticationId(int staffId);
    List<Authentication> getAuthenticationList();
}

package com.propertyManagement.mapper.login;

import com.propertyManagement.pojo.Account;
import com.propertyManagement.pojo.ManageStaff;
import com.propertyManagement.pojo.Staff;

import java.util.List;

public interface LoginMapper {
    List<ManageStaff> list();
    void addStaff(Staff staff);
    List<Account> listAccount();
    void addAccount(Account account);
    String getPasswordByPhone(String phone);
}

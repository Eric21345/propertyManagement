package com.propertyManagement.mapper.clientManagement;


import com.propertyManagement.pojo.Client;

import java.util.List;

public interface ClientMapper {
    List<Client> selectClient(String name, String company, String phone, String address);
    List<Client> selectClient2(String name, String company, String phone);
    List<Client> selectClient3(String name, String company, String address);
    List<Client> selectClient4(String name, String phone, String address);
    List<Client> selectClient5(String company, String phone, String address);
    List<Client> selectClient6(String name, String company);
    List<Client> selectClient7(String name, String phone);
    List<Client> selectClient8(String name, String address);
    List<Client> selectClient9(String company, String phone);
    List<Client> selectClient10(String company, String address);
    List<Client> selectClient11(String phone, String address);
    List<Client> selectClient12(String name);
    List<Client> selectClient13(String company);
    List<Client> selectClient14(String phone);
    List<Client> selectClient15(String address);
    List<Client> selectClient16();
    void deleteByPhone(String phone);
    void addClient(Client client);

}

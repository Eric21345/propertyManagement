package com.propertyManagement.service.clientManagement;

import com.propertyManagement.mapper.clientManagement.ClientMapper;
import com.propertyManagement.pojo.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientMapper clientMapper;

    public void deleteByPhone(String phone){clientMapper.deleteByPhone(phone);}
    public void addClient(Client client){clientMapper.addClient(client);}
    public List<Client> selectClient(String name, String company, String phone, String address){
        return clientMapper.selectClient(name, company, phone, address);
    }
    public List<Client> selectClient2(String name, String company, String phone){
        return clientMapper.selectClient2(name, company, phone);
    }
    public List<Client> selectClient3(String name, String company, String address){
        return clientMapper.selectClient3(name, company, address);
    }
    public List<Client> selectClient4(String name, String phone, String address){
        return clientMapper.selectClient4(name, phone, address);
    }
    public List<Client> selectClient5(String company, String phone, String address){
        return clientMapper.selectClient5(company, phone, address);
    }
    public List<Client> selectClient6(String name, String company){
        return clientMapper.selectClient6(name, company);
    }
    public List<Client> selectClient7(String name, String phone){
        return clientMapper.selectClient7(name, phone);
    }
    public List<Client> selectClient8(String name, String address){
        return clientMapper.selectClient8(name, address);
    }
    public List<Client> selectClient9(String company, String phone){
        return clientMapper.selectClient9(company, phone);
    }
    public List<Client> selectClient10(String company, String address){
        return clientMapper.selectClient10(company, address);
    }
    public List<Client> selectClient11(String phone, String address){
        return clientMapper.selectClient11(phone, address);
    }
    public List<Client> selectClient12(String name){
        return clientMapper.selectClient12(name);
    }
    public List<Client> selectClient13(String company){
        return clientMapper.selectClient13(company);
    }
    public List<Client> selectClient14(String phone){
        return clientMapper.selectClient14(phone);
    }
    public List<Client> selectClient15(String address){
        return clientMapper.selectClient15(address);
    }
    public List<Client> selectClient16(){
        return clientMapper.selectClient16();
    }

}

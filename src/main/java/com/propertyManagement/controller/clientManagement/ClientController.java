package com.propertyManagement.controller.clientManagement;

import com.propertyManagement.pojo.Client;
import com.propertyManagement.service.clientManagement.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;


    //对客户姓名、电话以及所在公司名称的组合查询
    @ResponseBody
    @RequestMapping("lookup/{str}")
    public Map lookup(@PathVariable("str") String str){
        String[] terms = str.split("#", 4);
        String name = terms[0];
        String company = terms[1];
        String phone = terms[2];
        String address = terms[3];
        List<Client> clients;
        if(!name.equals("") && !company.equals("") && !phone.equals("") && !address.equals("")){
            clients = clientService.selectClient(name, company, phone, address);
        }
        else if(!name.equals("") && !company.equals("") && !phone.equals("") && address.equals("")){
            clients = clientService.selectClient2(name, company, phone);
        }
        else if(!name.equals("") && !company.equals("") && phone.equals("") && !address.equals("")){
            clients = clientService.selectClient3(name, company, address);
        }
        else if(!name.equals("") && company.equals("") && !phone.equals("") && !address.equals("")){
            clients = clientService.selectClient4(name, phone, address);
        }
        else if(name.equals("") && !company.equals("") && !phone.equals("") && !address.equals("")){
            clients = clientService.selectClient5(company, phone, address);
        }
        else if(!name.equals("") && !company.equals("") && phone.equals("") && address.equals("")){
            clients = clientService.selectClient6(name, company);
        }
        else if(!name.equals("") && company.equals("") && !phone.equals("") && address.equals("")){
            clients = clientService.selectClient7(name, phone);
        }
        else if(!name.equals("") && company.equals("") && phone.equals("") && !address.equals("")){
            clients = clientService.selectClient8(name, address);
        }
        else if(name.equals("") && !company.equals("") && !phone.equals("") && address.equals("")){
            clients = clientService.selectClient9(company, phone);
        }
        else if(name.equals("") && !company.equals("") && phone.equals("") && !address.equals("")){
            clients = clientService.selectClient10(company, address);
        }
        else if(name.equals("") && company.equals("") && !phone.equals("") && !address.equals("")){
            clients = clientService.selectClient11(phone, address);
        }
        else if(!name.equals("") && company.equals("") && phone.equals("") && address.equals("")){
            clients = clientService.selectClient12(name);
        }
        else if(name.equals("") && !company.equals("") && phone.equals("") && address.equals("")){
            clients = clientService.selectClient13(company);
        }
        else if(name.equals("") && company.equals("") && !phone.equals("") && address.equals("")){
            clients = clientService.selectClient14(phone);
        }
        else if(name.equals("") && company.equals("") && phone.equals("") && !address.equals("")){
            clients = clientService.selectClient15(address);
        }
        else {
            clients = clientService.selectClient16();
        }
        Map map = new HashMap();
        map.put("clients", clients);
        return map;
    }

    //删除客户(手机号作为客户信息的唯一标识)
    @RequestMapping("delete")
    public void delete(String phone){
        clientService.deleteByPhone(phone);
    }

    //添加客户
    @RequestMapping("add")
    public void add(String name, String phone, String company, String address){
        Client client = new Client();
        client.setName(name);
        client.setPhone(phone);
        client.setCompany(company);
        client.setAddress(address);
        clientService.addClient(client);
    }

    //对客户信息进行编辑和修改
}

package com.propertyManagement.controller.clientManagement;

import com.propertyManagement.pojo.Client;
import com.propertyManagement.service.clientManagement.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;

    @SuppressWarnings("unchecked")
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
//        if(!name.equals("") && !Company.equals("") && !phone.equals("") && !address.equals("")){
//            clients = clientService.selectClient(name, Company, phone, address);
//        }
//        else if(!name.equals("") && !Company.equals("") && !phone.equals("") && address.equals("")){
//            clients = clientService.selectClient2(name, Company, phone);
//        }
//        else if(!name.equals("") && !Company.equals("") && phone.equals("") && !address.equals("")){
//            clients = clientService.selectClient3(name, Company, address);
//        }
//        else if(!name.equals("") && Company.equals("") && !phone.equals("") && !address.equals("")){
//            clients = clientService.selectClient4(name, phone, address);
//        }
//        else if(name.equals("") && !Company.equals("") && !phone.equals("") && !address.equals("")){
//            clients = clientService.selectClient5(Company, phone, address);
//        }
//        else if(!name.equals("") && !Company.equals("") && phone.equals("") && address.equals("")){
//            clients = clientService.selectClient6(name, Company);
//        }
//        else if(!name.equals("") && Company.equals("") && !phone.equals("") && address.equals("")){
//            clients = clientService.selectClient7(name, phone);
//        }
//        else if(!name.equals("") && Company.equals("") && phone.equals("") && !address.equals("")){
//            clients = clientService.selectClient8(name, address);
//        }
//        else if(name.equals("") && !Company.equals("") && !phone.equals("") && address.equals("")){
//            clients = clientService.selectClient9(Company, phone);
//        }
//        else if(name.equals("") && !Company.equals("") && phone.equals("") && !address.equals("")){
//            clients = clientService.selectClient10(Company, address);
//        }
//        else if(name.equals("") && Company.equals("") && !phone.equals("") && !address.equals("")){
//            clients = clientService.selectClient11(phone, address);
//        }
//        else if(!name.equals("") && Company.equals("") && phone.equals("") && address.equals("")){
//            clients = clientService.selectClient12(name);
//        }
//        else if(name.equals("") && !Company.equals("") && phone.equals("") && address.equals("")){
//            clients = clientService.selectClient13(Company);
//        }
//        else if(name.equals("") && Company.equals("") && !phone.equals("") && address.equals("")){
//            clients = clientService.selectClient14(phone);
//        }
//        else if(name.equals("") && Company.equals("") && phone.equals("") && !address.equals("")){
//            clients = clientService.selectClient15(address);
//        }
//        else {
//            clients = clientService.selectClient16();
//        }
        Map map = new HashMap();
//        map.put("clients", clients);
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
//        clientService.addClient(client);
    }

    //对客户信息进行编辑和修改
    @RequestMapping("edit")
    public Map edit(int id, String name, String company, String phone, String address){
        //修改客户信息需要注意客户修改后的手机号不能是数据库中已经存在的
        List<Client> clients = clientService.selectClient16();
        Map map = new HashMap();
        boolean flag = true;
        for(Client client:clients){
            if(client.getPhone().equals(phone)){
                map.put("msg", "您期望修改的手机号已存在");
                flag = false;
            }
        }
        if(flag){
            Client client =new Client();
            client.setId(id);
            client.setAddress(address);
            client.setPhone(phone);
            client.setCompany(company);
            client.setName(name);
            clientService.editClient(client);
            map.put("msg", "修改成功");
        }
        else {

        }
        return map;
    }

    //获取客户列表
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getClientList")
    public Map getClientList(){
      Map map = new HashMap();
      List<Client> clientList = clientService.getClientList();
      map.put("clientList", clientList);
      map.put("status", 1);
      return map;
    }

    //获取客户信息
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getClientInfo")
    public Map getClientInfo(@RequestParam("id") int id){
        System.out.println(id);
        Client client = clientService.getClientInfoById(id);
        Map map = new HashMap();
        map.put("status", 1);
        map.put("client", client);
        return map;
    }
}

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

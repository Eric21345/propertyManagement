package com.propertyManagement.controller.login;


import com.propertyManagement.pojo.ManageStaff;
import com.propertyManagement.pojo.Staff;
import com.propertyManagement.service.Login.LoginService;
import com.propertyManagement.util.AesCbcUtil;
import com.propertyManagement.util.HttpRequest;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @SuppressWarnings("unchecked")
    @ResponseBody
    //微信小程序登录
    @RequestMapping("wxLogin")
    public Map decodeUserInfo(String encryptedData, String iv, String code){
        System.out.println("连上后台啦");
        Map map = new HashMap();
        //登录凭证不能为空
        if(code == null || code.length() == 0){
            map.put("status", 0);
            map.put("msg", "code不能为空");
        }

        //小程序的唯一标识
        String wxAppId = "";
        //小程序的app secret
        String wxAppSecret = "";
        //授权
        String grantType = "";
        //请求参数
        String params = "appid=" + wxAppId + "&secret=" + wxAppSecret + "&js_code" + code + "&grant_type=" + grantType;
        //发送请求
        String str = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session",params);
        //解析相应内容
       JSONObject json = JSONObject.fromObject(str);

        System.out.println("这里是openid和session_key" + json);
        String session_key = (String) json.get("session_key");
        String openid = (String) json.get("openid");

        try {
            System.out.println("进入解密成功程序");
            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "utf-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");
                HashMap userinfo = new HashMap<>();
                userinfo.put("openid", json.get("openid"));
                userinfo.put("session_key", json.get("session_key"));
                map.put("userInfo", userinfo);
            }
        }
        catch (Exception e){
            System.out.println("解密错误");
        }

        return map;
    }
    /*
    管理人员注册
    管理人员注册时，需要判断数据库中是否存在这个人，判断依据身份证号码。如果存在这个人，即将其微信账号的唯一标识openid与
    其相关信息存入一张总的staff表中,返回注册是否成功的标识
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("register")
    @ResponseBody
    public Map register(@RequestParam(value = "name") String name,
                         @RequestParam(value = "sex") String sex,
                         @RequestParam(value = "idCard") String idCard,
                         @RequestParam(value = "birthDate") String birthDate,
                         @RequestParam(value = "workDate") String workDate,
                         @RequestParam(value = "openid") String openid){
        System.out.println(name + "," + sex + "," + idCard + "," + birthDate + "," + workDate + "," + openid);
        List<ManageStaff> manageStaffs = loginService.listManageStaff();
        Map map = new HashMap();
        boolean flag = false;
        for(ManageStaff manageStaff:manageStaffs){
            if(manageStaff.getName().equals(name)){
                flag = true;
            }
        }
        if(flag){
            Staff staff = new Staff();
            staff.setName(name);
            staff.setSex(sex);
            staff.setIdCard(idCard);
            staff.setOpenid(openid);
            //字符串转date
            staff.setBirthDate(Date.valueOf(birthDate));
            staff.setWorkDate(Date.valueOf(workDate));
            loginService.addStaff(staff);
            map.put("msg", "注册成功");
            map.put("status", 1);
        }
        else {
            map.put("msg", "您不是合法用户");
            map.put("status", 0);
        }
        return map;
    }



}

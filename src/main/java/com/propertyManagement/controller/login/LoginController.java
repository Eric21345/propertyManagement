package com.propertyManagement.controller.login;


import com.propertyManagement.pojo.Account;
import com.propertyManagement.pojo.ManageStaff;
import com.propertyManagement.pojo.Staff;
import com.propertyManagement.service.Login.LoginService;
import com.propertyManagement.util.AesCbcUtil;
import com.propertyManagement.util.HttpRequest;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private String identifyingCode2;

    @SuppressWarnings("unchecked")
    @ResponseBody
    //微信小程序登录
    @RequestMapping("wxLogin")
    public Map decodeUserInfo(String encryptedData, String iv, String code){
        System.out.println("encrytedData的值:" + encryptedData + "iv的值:" + iv + "code的值:" + code);
        Map map = new HashMap();
        //登录凭证不能为空
        if(code == null || code.length() == 0){
            System.out.println("凭证为空");
            map.put("status", 0);
            map.put("msg", "code不能为空");
        }

        //小程序的唯一标识
        String wxAppId = "wx9e4a9cdd231690a7";
        //小程序的app secret
        String wxAppSecret = "72dce88bdc8e622a0727af89369f419b";
        //授权
        String grantType = "authorization_code";
        //请求参数
        String params = "appid=" + wxAppId + "&secret=" + wxAppSecret + "&js_code=" +
                "" + code + "&grant_type=" + grantType;
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

//    @SuppressWarnings("unchecked")
//    @RequestMapping("register")
//    @ResponseBody
//    public Map register(@RequestParam(value = "name") String name,
//                         @RequestParam(value = "sex") String sex,
//                         @RequestParam(value = "idCard") String idCard,
//                         @RequestParam(value = "birthDate") String birthDate,
//                         @RequestParam(value = "workDate") String workDate,
//                         @RequestParam(value = "openid") String openid){
//        System.out.println(name + "," + sex + "," + idCard + "," + birthDate + "," + workDate + "," + openid);
//        List<ManageStaff> manageStaffs = loginService.listManageStaff();
//        Map map = new HashMap();
//        boolean flag = false;
//        for(ManageStaff manageStaff:manageStaffs){
//            if(manageStaff.getName().equals(name)){
//                flag = true;
//            }
//        }
//        if(flag){
//            Staff staff = new Staff();
//            staff.setName(name);
//            staff.setSex(sex);
//            staff.setIdCard(idCard);
//            staff.setOpenid(openid);
//            //字符串转date
//            staff.setBirthDate(Date.valueOf(birthDate));
//            staff.setWorkDate(Date.valueOf(workDate));
//            loginService.addStaff(staff);
//            map.put("msg", "注册成功");
//            map.put("status", 1);
//        }
//        else {
//            map.put("msg", "您不是合法用户");
//            map.put("status", 0);
//        }
//        return map;
//    }

    //获取验证码
    @SuppressWarnings("uncheck")
    @RequestMapping("getIdentifyingCode")
    public Map getIdentifyingCode(@RequestParam(value = "phone") String phone){
        Map map = new HashMap();




        map.put("status", 1);
        map.put("msg", "获取验证码成功");
        return map;
    }

    //手机号注册
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("phoneRegister")
    public Map phoneRegister(@RequestParam(value = "phone2")String phone,
                             @RequestParam(value = "identifyingCode") String identifyingCode,
                             @RequestParam(value = "password2") String password,
                             @RequestParam(value = "openid") String openid){
        System.out.println(phone + " " + identifyingCode + " " + password + " " + openid);
        Map map = new HashMap();
        boolean flag = false;
        List<Account> accountList = loginService.listAccount();
        for(Account account:accountList){
            if(account.getPhone().equals(phone)){
                flag = true;
            }
        }
        if(flag){
            map.put("msg", "该手机号已注册");
            map.put("status", 0);
        }
        else {
             if(identifyingCode.equals(identifyingCode2)){
                Account account = new Account();
                account.setPhone(phone);
                account.setOpenid(openid);
                account.setPassword(password);
                loginService.addAccount(account);
                map.put("msg", "注册成功");
                map.put("status", 1);
            }
            else {
                map.put("msg", "验证码有误");
                map.put("status", 0);
            }
        }
        return map;
    }

    //手机号登录
    /**
     * 这里需要防止同一账号重复登录
     *
     * **/
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "phoneLogin", method = RequestMethod.POST)
    public Map phoneLogin(@RequestParam(value = "phone") String phone,
                          @RequestParam(value = "password") String password){

        String password2 = loginService.getPasswordByPhone(phone);
        System.out.println(password2);
        Map map = new HashMap();
        if(password.equals(password2)){
            map.put("status", 1);
            map.put("msg", "登录成功");
        }
        else {
            map.put("status", 0);
            map.put("msg", "用户名或密码错误");
        }
        return map;
    }

    /**
     * 判断是否出现顶号
     * **/


}

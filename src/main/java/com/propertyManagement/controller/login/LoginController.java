package com.propertyManagement.controller.login;


import com.propertyManagement.pojo.*;
import com.propertyManagement.service.Login.AuthenticationService;
import com.propertyManagement.service.Login.LoginService;
import com.propertyManagement.service.staffManagement.StaffService;
import com.propertyManagement.util.AesCbcUtil;
import com.propertyManagement.util.HttpRequest;
//import com.sun.org.glassfish.external.amx.AMX;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.sql.SQLTransactionRollbackException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private AuthenticationService authenticationService;

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
        String wxAppId = "wxbbb4709fb682a3a2";
        //小程序的app secret
        String wxAppSecret = "12c22d53a9c8476f143f572a6eabc80e";
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
                userinfo.put("openId", json.get("openid"));
                userinfo.put("session_key", json.get("session_key"));
                map.put("userInfo", userinfo);
            }
        }
        catch (Exception e){
            System.out.println("解密错误");
        }

        return map;
    }

    //判断openId是否在数据库中实名注册
    @SuppressWarnings("unchecked")
    @RequestMapping("authentication")
    @ResponseBody
    public Map authentication(@RequestParam("openId") String openId){
        Map map = new HashMap();
        Authentication authentication = authenticationService.getAuthenticationByOpenId(openId);
        if(authentication == null){
            map.put("state", -1);
        }else {
            int state = authentication.getState();
            if(state == 0) map.put("state", 0);
            else map.put("state", 1);
            Staff staff = staffService.getStaffByOpenId(openId);
            map.put("staffInfo", staff);
        }

        map.put("status", 1);
        return map;
    }

    //员工提交认证申请
    @SuppressWarnings("unchecked")
    @RequestMapping("authenticationApply")
    @ResponseBody
    public Map authenticationApply(@RequestParam("name") String name,
                                   @RequestParam("companyId") int companyId,
                                   @RequestParam("idCard") String idCard,
                                   @RequestParam("sex") String sex,
                                   @RequestParam("position") int  position,
                                   @RequestParam("birthDate") String birthDate,
                                   @RequestParam("workDate") String workDate,
                                   @RequestParam("phone") String phone,
                                   @RequestParam("openId") String openId) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Staff staff = new Staff();
        staff.setName(name);
        staff.setCompanyId(companyId);
        staff.setIdCard(idCard);
        staff.setSex(sex);
        staff.setPosition(position);
        staff.setBirthDate(new Date(sdf.parse(birthDate).getTime()));
        staff.setWorkDate(new Date(sdf.parse(workDate).getTime()));
        staff.setPhone(phone);
        staff.setType(0);
        //这里需要对authentication表和staff表进行操作
        staffService.addApplyStaff(staff);
        int staffId = staff.getId();
        System.out.println(staffId);
        //将staff的id与openId一起存入authentication表
        authenticationService.addAuthentication(openId, staffId, 0);
        Map map = new HashMap();
        map.put("status", 1);
        return map;
    }

    //获取公司列表
    @SuppressWarnings("unchecked")
    @RequestMapping("getCompanyList")
    @ResponseBody
    public Map getCompanyList(){
        Map map = new HashMap();
        List<Company> companyList = authenticationService.getCompanyList();
        map.put("companyList", companyList);
        map.put("status", 1);
        return map;
    }

    //依据公司id获取公司名称
    @SuppressWarnings("unchecked")
    @RequestMapping("getCompanyNameById")
    @ResponseBody
    public Map getCompanyNameById(@RequestParam("id") int id){
        Map map = new HashMap();
        String companyName = loginService.getCompanyNameById(id);
        map.put("status", 1);
        map.put("companyName", companyName);
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


}

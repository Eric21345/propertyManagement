package com.propertyManagement.util;
import com.alibaba.fastjson.JSONObject;
import com.propertyManagement.pojo.Account;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TokenInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("这里是拦截器");
        String token = request.getHeader("token");
        String accountId = request.getHeader("accountId");
        if(token != null && accountId != null){
            Account account = JWT.unsign(token, Account.class);
            if(account != null){
                if(account.getId() == Integer.parseInt(accountId)) return true;
                else {
                    responseMessage(response, response.getWriter());
                    return false;
                }
            }
            else {
                responseMessage(response, response.getWriter());
                return false;
            }
        }
        else {
            responseMessage(response, response.getWriter());
            return false;
        }
    }

    private void responseMessage(HttpServletResponse response, PrintWriter out){
        response.setContentType("application/json; charset=utf-8");
        Map map = new HashMap();
        map.put("status", -1);
        String json = JSONObject.toJSONString(map);
        out.print(json);
        out.flush();
        out.close();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}

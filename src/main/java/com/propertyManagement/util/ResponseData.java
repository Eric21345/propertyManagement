package com.propertyManagement.util;

import java.util.LinkedHashMap;

public class ResponseData extends LinkedHashMap<String, Object> {

    private static final long serialVersionUID = -364546270975223015L;

//    public ResponseData result(String key, Object value) {
//        this.put(key, value);
//        return this;
//    }

    public ResponseData success(){
        return this.success("success");
    }
    public ResponseData success(Object message){
        this.put("code",200);
        this.put("message", message);
        return this;
    }

    public ResponseData fail(){
        return this.fail("fail");
    }

    public ResponseData fail(Object message) {
        this.put("code",400);
        this.put("message", message);
        return this;
    }

    public ResponseData redirect(String message) {
        this.put("code",300);
        this.put("message", message);
        return this;
    }

    public ResponseData unauthorized() {
        return this.unauthorized("the current user is unauthorized");
    }
    public ResponseData unauthorized(Object message){
        this.put("code",401);
        this.put("message", message);
        return this;
    }

    public ResponseData forbidden(Object message){
        this.put("code",403);
        this.put("message", message);
        return this;
    }

//    public ResponseData code(int code) {
//        return result("code",code);
//    }
//
//    public ResponseData message(String message) {
//        return result("message", message);
//    }
//
//    public ResponseData data(Object data) {
//        return result("data", data);
//    }
}
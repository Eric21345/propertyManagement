package com.propertyManagement.util.exception;

import com.propertyManagement.util.ResponseData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

//自定义全局相应异常
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ResponseData jsonExceptionHandler(HttpServletRequest request, MyException e) {
        return new ResponseData().fail(e.getMessage());
    }
}

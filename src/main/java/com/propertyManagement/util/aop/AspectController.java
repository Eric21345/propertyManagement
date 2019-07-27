package com.propertyManagement.util.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;

public class AspectController {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AspectController.class);

    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        System.out.println("方法名:"+methodSignature.getMethod().getName()+" 参数列表:"+ArrayToParameterString(methodSignature.getParameterNames(),joinPoint.getArgs()));
    }

    private  String ArrayToParameterString(String[] parameterNames,Object[] parameterValues) {
        StringBuffer sb = new StringBuffer();
        if (parameterNames != null && parameterNames.length > 0) {
            for (int i = 0; i < parameterNames.length; i++) {
                sb.append(parameterNames[i]);
                sb.append(":");
                sb.append(parameterValues[i]);
                sb.append(";");
            }
        }
        return sb.toString();
    }
}

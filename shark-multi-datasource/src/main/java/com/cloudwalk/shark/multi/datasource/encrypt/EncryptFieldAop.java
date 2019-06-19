package com.cloudwalk.shark.multi.datasource.encrypt;

import com.cloudwalk.shark.common.utils.AseUtil;
import com.cloudwalk.shark.multi.datasource.encrypt.annotation.EncryptField;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * 安全字段加密解密切面
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Aspect
@Component
public class EncryptFieldAop {
    @Value("${secretkey}")
    private String secretKey;

    @Pointcut("@annotation(com.cloudwalk.shark.multi.datasource.encrypt.annotation.EncryptMethod)")
    public void annotationPointCut() {
    }

    @Around("annotationPointCut()")
    // 直接向上抛异常,统一放到controller中去全局捕获
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object responseObj = null;
        Object requestObj = joinPoint.getArgs()[0];
        handleEncrypt(requestObj);
        responseObj = joinPoint.proceed();
        handleDecrypt(responseObj);
        return responseObj;
    }

    /**
     * 处理加密
     *
     * @param requestObj
     */
    private void handleEncrypt(Object requestObj) throws IllegalAccessException {
        if (Objects.isNull(requestObj)) {
            return;
        }
        Field[] fields = requestObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean hasSecureField = field.isAnnotationPresent(EncryptField.class);
            if (hasSecureField) {
                field.setAccessible(true);
                if (field.get(requestObj) instanceof String) {
                    String plaintextValue = (String) field.get(requestObj);
                    String encryptValue = AseUtil.encrypt(plaintextValue, secretKey);
                    field.set(requestObj, encryptValue);
                } else {
                    throw new RuntimeException("加密字段类型只能是String");
                }

            }
        }
    }


    /**
     * 处理解密
     *
     * @param responseObj
     */
    private Object handleDecrypt(Object responseObj) throws IllegalAccessException {
        if (Objects.isNull(responseObj)) {
            return null;
        }

        Field[] fields = responseObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean hasSecureField = field.isAnnotationPresent(EncryptField.class);
            if (hasSecureField) {
                field.setAccessible(true);
                if(field.get(responseObj) instanceof String){
                    String encryptValue = (String) field.get(responseObj);
                    String plaintextValue = AseUtil.decrypt(encryptValue, secretKey);
                    field.set(responseObj, plaintextValue);
                }
            }
        }
        return responseObj;
    }
}

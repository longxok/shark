package com.cloudwalk.shark.encrypt;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 *  安全字段注解
 * 加在需要加密/解密的方法上
 * 实现自动加密解密
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface EncryptMethod {
}

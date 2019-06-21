package com.cloudwalk.shark.config.annotation;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import java.lang.annotation.*;


/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.config.annotation
 * @date:2019/6/21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
//最高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RequestLimit {
    /**
     *
     * 允许访问的次数，默认值MAX_VALUE
     */
    int count() default Integer.MAX_VALUE;

    /**
     *
     * 时间段，单位为毫秒，默认值一分钟
     */
    long time() default 60000;
}
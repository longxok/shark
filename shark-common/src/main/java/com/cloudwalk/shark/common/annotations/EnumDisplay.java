package com.cloudwalk.shark.common.annotations;


import java.lang.annotation.*;

/**
 * @author kevin
 * @date 2017年9月27日上午11:36:07
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnumDisplay {

    /**
     * value
     *
     * @return
     */
    String value();

    /**
     * Order
     *
     * @return
     */
    int order() default 0;
}

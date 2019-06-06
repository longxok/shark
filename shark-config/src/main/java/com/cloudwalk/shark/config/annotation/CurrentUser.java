package com.cloudwalk.shark.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @BelongsProject: JDTaste
 * @BelongsPackage: com.jdtaste.common.util
 * @Author:
 * @CreateTime: 2018-07-04 15:39
 * @Description: 在Controller的方法参数中使用此注解，该方法在映射时会注入当前登录的User对象
 */
@Target(ElementType.PARAMETER)          // 可用在方法的参数上
@Retention(RetentionPolicy.RUNTIME)     // 运行时有效
public @interface CurrentUser {
}
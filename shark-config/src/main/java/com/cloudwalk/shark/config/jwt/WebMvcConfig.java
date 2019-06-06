package com.cloudwalk.shark.config.jwt;

import com.cloudwalk.shark.config.annotation.CurrentUserMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.util.List;

/**
 * @BelongsProject:
 * @BelongsPackage: com.jdtaste.jdtastesso.conf
 * @Author:
 * @CreateTime: 2018-07-04 10:03
 * @Description: 配置URLInterceptor拦截器，以及拦截路径
 */
//@Configuration
public class WebMvcConfig  {

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new CurrentUserMethodArgumentResolver());
    }
}
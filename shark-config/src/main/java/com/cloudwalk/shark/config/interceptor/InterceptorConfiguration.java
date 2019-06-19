package com.cloudwalk.shark.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: 拦截器
 * @date:2019/6/19
 */


@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Autowired
    private AbstractAuthenticationInterceptor abstractAuthenticationInterceptor;

    @Autowired
    private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(abstractAuthenticationInterceptor).addPathPatterns("/**");
    }

    @Bean
    CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver() {
        return new CurrentUserMethodArgumentResolver();
    }

    /**
     * 跨域支持
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600 * 24);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver);
    }

    @ConditionalOnProperty(value = {"queue-filter.enabled"})
    @Bean
    public AbstractAuthenticationInterceptor queueKickOutInterceptor() {
        return new QueueKickOutInterceptor();
    }

    @ConditionalOnMissingBean(AbstractAuthenticationInterceptor.class)
    @Bean
    public AbstractAuthenticationInterceptor compareKickOutInterceptor() {
        return new CompareKickOutInterceptor();
    }

}
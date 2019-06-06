package com.cloudwalk.shark.config.appConfig;

import com.cloudwalk.shark.config.intercept.CommonMethodInterceptor;
import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;

//@Configuration
public class AppConfig {
    //要创建代理的目标Bean
   /* @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }*/
    //创建Advice或Advisor
    @Bean
    public Advice myMethodInterceptor(){
        return new CommonMethodInterceptor();
    }
    //使用BeanNameAutoProxyCreator来创建代理
    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator(){
        BeanNameAutoProxyCreator beanNameAutoProxyCreator=new BeanNameAutoProxyCreator();
        //设置要创建代理的那些Bean的名字
        beanNameAutoProxyCreator.setBeanNames("userSer*");
        //设置拦截链名字(这些拦截器是有先后顺序的)
        beanNameAutoProxyCreator.setInterceptorNames("myMethodInterceptor");
        return beanNameAutoProxyCreator;
    }
}

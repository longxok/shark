package com.cloudwalk.shark.mybatis.design.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SharkProxyDesignPattern {
    private Object target;

    public SharkProxyDesignPattern(Object object){
        this.target = object;
    }
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),new InvocationHandler(){

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(target,args);
            }
        });
    }



}

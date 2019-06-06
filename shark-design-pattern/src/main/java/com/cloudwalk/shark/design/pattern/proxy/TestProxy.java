package com.cloudwalk.shark.design.pattern.proxy;

import com.cloudwalk.shark.design.pattern.proxy.impl.UserInfoImpl;
import com.cloudwalk.shark.design.pattern.proxy.inter.IUserInfo;

import java.lang.reflect.Proxy;

public class TestProxy {
    public static void main(String[] args){
        IUserInfo userInfo = new UserInfoImpl();
        IUserInfo  userInfo2= (IUserInfo)new SharkProxyDesignPattern(userInfo).getProxyInstance();
        userInfo2.sayHello();
    }
}

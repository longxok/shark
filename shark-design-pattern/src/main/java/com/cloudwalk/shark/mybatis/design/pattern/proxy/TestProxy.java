package com.cloudwalk.shark.mybatis.design.pattern.proxy;

import com.cloudwalk.shark.mybatis.design.pattern.proxy.impl.UserInfoImpl;
import com.cloudwalk.shark.mybatis.design.pattern.proxy.inter.IUserInfo;

public class TestProxy {
    public static void main(String[] args){
        IUserInfo userInfo = new UserInfoImpl();
        IUserInfo  userInfo2= (IUserInfo)new SharkProxyDesignPattern(userInfo).getProxyInstance();
        userInfo2.sayHello();
    }
}

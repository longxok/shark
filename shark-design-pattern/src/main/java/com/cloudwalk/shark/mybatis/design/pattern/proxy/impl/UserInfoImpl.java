package com.cloudwalk.shark.mybatis.design.pattern.proxy.impl;

import com.cloudwalk.shark.mybatis.design.pattern.proxy.inter.IUserInfo;

public class UserInfoImpl implements IUserInfo {
    public void sayHello() {
        System.out.println("12");
    }
}

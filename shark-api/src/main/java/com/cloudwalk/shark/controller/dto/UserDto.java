package com.cloudwalk.shark.controller.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class UserDto extends UserFathderDto{
    @JSONField(name = "myName")
    private String userName;
    private String age;

    private String score;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}

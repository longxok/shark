package com.cloudwalk.shark.controller.mapper;

import com.cloudwalk.shark.controller.model.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    public int insertUser(User user);
    public User findUserByName(String name);

    List<User> queryUserByName(List<String> userNameList);

    List<User> queryAllUser();

    public void  getUserTest();

    User queryUsersByName(Map map);

    int updateUser(User user);
}

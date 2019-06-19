package com.cloudwalk.shark.service;

import com.cloudwalk.shark.multi.datasource.multiDataSource.annotation.DataSourceKey;
import com.cloudwalk.shark.controller.model.User;

import java.util.List;
import java.util.Map;


public interface UserService {
    public void addUser(User user);

    public void updateUser(User user);

    public User findUserByName(String name);

    @DataSourceKey("dataSource")
    public List<User> queryUserByName(List<String> userNameList);

    public List<User> queryAllUser();

    public User queryUsersByName(Map map);
}

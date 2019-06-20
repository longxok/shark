package com.cloudwalk.shark.mybatis.dao;

import com.cloudwalk.shark.mybatis.mapper.UserMapper;
import com.cloudwalk.shark.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserDao {
    @Autowired
    private UserMapper userMapper;

    public int addUser(User user){
        return userMapper.insertUser(user);
    }

    public int updateUser(User user){
        return userMapper.updateUser(user);
    }

    public User findUserByName(String name){
        return userMapper.findUserByName(name);
    }

    public List<User> queryUserByName(List<String> userNameList) {
        return userMapper.queryUserByName(userNameList);
    }

    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }

    public User queryUsersByName(Map map) {
        return userMapper.queryUsersByName(map);
    }
}

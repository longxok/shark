package com.cloudwalk.shark.service.impl;

import com.cloudwalk.shark.multi.datasource.encrypt.annotation.EncryptMethod;
import com.cloudwalk.shark.controller.dao.UserDao;
import com.cloudwalk.shark.controller.model.User;
import com.cloudwalk.shark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @EncryptMethod
    public void addUser(User user) {
        userDao.addUser(user);
    }


    public void updateUser(User user) {
        userDao.updateUser(user);
    }
  //  @Cacheable("userName")
    @EncryptMethod
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    public List<User> queryUserByName(List<String> userNameList) {
        return userDao.queryUserByName(userNameList);
    }

    public List<User> queryAllUser() {
        return userDao.queryAllUser();
    }

    public User queryUsersByName(Map map) {
        return userDao.queryUsersByName(map);
    }
}

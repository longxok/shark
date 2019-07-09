package com.springboot.service.impl;

import com.springboot.bean.User;
import com.springboot.service.UserService;
import org.springframework.stereotype.Repository;

@Repository("userService")
public class UserServiceImpl extends BaseService<User> implements UserService{

	
}

package com.springboot.dao;

import com.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	User findByUserName(String userName);
}

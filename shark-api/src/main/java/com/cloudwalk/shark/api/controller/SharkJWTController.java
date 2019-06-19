package com.cloudwalk.shark.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloudwalk.shark.config.utils.TokenUtils;
import com.cloudwalk.shark.config.annotation.CurrentUser;
import com.cloudwalk.shark.config.annotation.LoginRequired;
import com.cloudwalk.shark.api.dto.UserDto;
import com.cloudwalk.shark.api.model.User;
import com.cloudwalk.shark.service.UserService;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping(value = "/jwt", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class SharkJWTController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedissonClient redissonClient;

    @PostMapping("")
    @ResponseBody
    public Object login(@RequestBody UserDto userDto) {
        User userInDataBase = userService.findUserByName(userDto.getUserName());
        JSONObject jsonObject = new JSONObject();
        if (userInDataBase == null) {
            jsonObject.put("error", "用户不存在");
        }  else {
            String token = TokenUtils.createJwtToken(userInDataBase.getUserName());
            RBucket rBucket = redissonClient.getBucket(token);
            rBucket.delete();
            rBucket.set(userInDataBase, TokenUtils.EXPIRE_TIME_MS, TimeUnit.MILLISECONDS);
            jsonObject.put("token", token);
            jsonObject.put("user", userInDataBase);

        }
        return jsonObject;
    }



    @LoginRequired
    @PostMapping("loginRequire")
    @ResponseBody
    public Object loginTest(@CurrentUser User user, HttpServletRequest request) {
        User userTemp = (User) request.getAttribute("currentUser");
        System.out.println(user.getUserName());
        System.out.println(userTemp.getUserName());

        return userTemp;
    }
}

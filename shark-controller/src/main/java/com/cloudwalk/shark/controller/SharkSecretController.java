package com.cloudwalk.shark.controller;

import com.cloudwalk.shark.common.utils.ResponseData;
import com.cloudwalk.shark.dto.UserDto;
import com.cloudwalk.shark.model.User;
import com.cloudwalk.shark.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/encrypt", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class SharkSecretController {

    @Autowired
    private UserService userService;

    @PostMapping("/bean")
    @ResponseBody
    public ResponseData checkBeanIsValid(@RequestBody UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        userService.addUser(user);

        User userQuery = userService.findUserByName(userDto.getUserName());
        return new ResponseData(true,"2","3",userQuery);
    }
}

package com.cloudwalk.shark.portal;

import com.cloudwalk.shark.model.User;
import com.cloudwalk.shark.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = {"com.*"})
@PropertySource(value = {"classpath:jdbc.properties","classpath:application.yml"})
public class SharkPortalTest {
    @Autowired
    private UserService userService;
    @Test
    public void insert(){
        User user = new User();
        user.setUserName("213");
        userService.addUser(user);
    }

}

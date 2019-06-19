package com.cloudwalk.shark.controller.controller;

import com.cloudwalk.shark.config.profiler.Profiler;
import com.cloudwalk.shark.config.utils.LockUtil;
import com.cloudwalk.shark.controller.model.User;
import com.cloudwalk.shark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping(value = "/lock", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class SharkDBLockController {
    @Autowired
    private UserService userService;
    @PostMapping("/db/{userName}")
    @ResponseBody
    public void updateUserScore(@PathVariable("userName") String userName, @RequestBody String body, HttpServletRequest request) throws InterruptedException {
       /* Profiler.reset();
        Profiler.init("分布式锁开始");*/
        try {
            if (LockUtil.tryLock("1", 1, 5, TimeUnit.SECONDS)) {
                System.out.println("接收到请求" + userName);
                User user = userService.findUserByName(userName);
                Thread.sleep(2000);
                user.setScore(user.getScore() + 1);
                userService.updateUser(user);
            } else {
                System.out.println("指定时间内没有获取到相应的锁！");
            }
        }finally {
           /* // 结束调用树
            Profiler.exit();*/
            // 打印调用树结果
            System.out.println(Profiler.dump());
            /*// 重置调用树
            Profiler.reset();*/
        }
    }
}
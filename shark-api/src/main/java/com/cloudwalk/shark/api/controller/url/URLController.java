package com.cloudwalk.shark.api.controller.url;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.api.controller.url
 * @date:2019/6/21
 */

import com.cloudwalk.shark.config.annotation.RequestLimit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
@Controller
public class URLController {
    @RequestLimit(count=2,time=60000)
    @RequestMapping("/urltest")
    @ResponseBody
    public String test(HttpServletRequest request, ModelMap modelMap) {
        return "aaa";
    }
}

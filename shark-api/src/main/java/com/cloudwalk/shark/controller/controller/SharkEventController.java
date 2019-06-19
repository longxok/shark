package com.cloudwalk.shark.controller.controller;

import com.cloudwalk.shark.config.event.custom.MyEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SharkEventController {
    @Autowired
    private MyEventService myEventService;


    @ResponseBody
    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public Object readiness(HttpServletRequest request) {
        myEventService.publish("First publish!");
        myEventService.publish("Second publish!");

        return ResponseEntity.status(500).body("Naming is not in readiness");
    }
}

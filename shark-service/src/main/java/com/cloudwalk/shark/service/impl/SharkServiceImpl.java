package com.cloudwalk.shark.service.impl;

import com.cloudwalk.shark.service.SharkServiceInter;
import org.springframework.stereotype.Component;
@Component
public class SharkServiceImpl implements SharkServiceInter {
    public String getSharkPort() {
        return "1111111";
    }
}

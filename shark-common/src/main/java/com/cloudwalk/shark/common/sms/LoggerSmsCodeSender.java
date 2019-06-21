package com.cloudwalk.shark.common.sms;

import com.cloudwalk.shark.common.asyn.Asyn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kevin
 * @date 2018-07-27 08:53
 */
public class LoggerSmsCodeSender implements SmsCodeSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerSmsCodeSender.class);

    @Override
    public void send(String mobile, String code, int expireSecond) {
        Asyn asyn = () -> LOGGER.info("[XXX] {},登陆验证码: {},请在 {} 分钟内完成验证.", mobile, code, expireSecond / 60);
        asyn.start();
    }
}

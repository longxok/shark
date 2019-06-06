package com.cloudwalk.shark.design.pattern.observer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestObserver {

    public static void main(String[] args){

        // 要验证的字符串
        String str = "v1.0.1212a";
        // 邮箱验证规则
        String regEx = "v\\d+(\\.\\d+)+(\\.\\d+)";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        System.out.println(rs);

    }
}

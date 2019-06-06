package com.cloudwalk.shark.interview.algorithm;

import java.util.Scanner;

/**
 * 【问题十一】一个5位数，判断它是不是回文数。即12321是回文数，个位与万位相同，十位与千位相同。
 */
public class Demo11 {
    private static Scanner in;
    public static void main(String[] args) {
        System.out.println("请输入数字：");
        in = new Scanner(System.in);
        String str = in.next();
        int l = Integer.parseInt(str);
        if (l < 10000 || l > 9999999) {
            System.out.println("请输入正确的五位数字！");
            System.exit(0);
        }
        boolean is = false;
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length / 2; i++) {
            if (ch[i] != ch[ch.length - i - 1]) {
                is = false;
                break;
            } else {
                is = true;
            }
        }
        if (is) {
            System.out.println("这是一个回文！");
        } else {
            System.out.println("不是一个回文！");
        }
    }
}

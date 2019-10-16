package com.cloudwalk.shark.interview.extension;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.extension
 * @date:2019/8/26
 */
public class Son extends Father {
    static {
        System.out.println("儿子在静态代码块");
    }
    public static int factor = 22;

    public Son() {
        System.out.println("我是儿子~");
    }
}
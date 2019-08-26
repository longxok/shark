package com.cloudwalk.shark.interview.extension;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.extension
 * @date:2019/8/26
 */
public class Father extends Grandpa{
    static {
        System.out.println("爸爸在静态代码块");
    }
    public static int factor = 25;

    public Father() {
        System.out.println("我是爸爸~");
    }
}
package com.cloudwalk.shark.interview.extension;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.extension
 * @date:2019/8/26
 */

/**
 * 这篇博文介绍的很详细，将类的【加载】+【验证、准备：
 * 内存分配的对象。Java 中的变量有「类变量」和「类成员变量」两种类型，
 * 「类变量」指的是被 static 修饰的变量，而其他所有类型的变量都属于「类成员变量」。
 * 在准备阶段，JVM 只会为「类变量」分配内存，而不会为「类成员变量」分配内存。
 * 「类成员变量」的内存分配需要等到初始化阶段才开始、解析】+【初始化】
 * https://blog.csdn.net/szxiaohe/article/details/84893405
 */
public class Book {
    public static void main(String[] args) {
        staticFunction();
    }
    //静态块
    static {
        System.out.println("书的静态代码块");
    }
    //静态对象
    static Book book = new Book();



    //代码块
    {
        System.out.println("书的普通代码块");
    }

    //构造方法
    Book() {
        System.out.println("书的构造方法");
        System.out.println("price=" + price + ",amount=" + amount);
    }

    //静态方法
    public static void staticFunction() {
        System.out.println("书的静态方法");
    }

    int price = 110;

    //静态变量
    static int amount = 112;
}
package com.cloudwalk.shark.interview.block;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.block
 * @date:2019/11/26
 */
public class Book {
    public static void main(String[] args) {
        staticFunction();
    }

    //静态对象
    static Book book = new Book();

    //静态块
    static {
        System.out.println("书的静态代码块");
    }

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
    final static int amount = 112;
}

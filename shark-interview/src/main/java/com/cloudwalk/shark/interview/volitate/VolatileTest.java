package com.cloudwalk.shark.interview.volitate;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.volitate
 * @date:2019/8/22
 */
public class VolatileTest {
    //如果把volatile去掉，进不了READER线程；
//不去掉的话UPDATER线程执行完毕执行READER线程 ，
//循环输出Update the value to。。。The value updated to 。。
    private static volatile int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 500;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
                if (localValue != INIT_VALUE) {
                    System.out.printf("The value updated to [%d]\n", INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        }, "READER").start();

        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.printf("Update the value to [%d]\n", ++localValue);
                INIT_VALUE = localValue;
            }
        }, "UPDATER").start();
    }
}
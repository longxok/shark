package com.cloudwalk.shark.interview.oom.stackOverflowError;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.oom.stackOverflowError
 * @date:2019/7/16
 */
public class StackOverflowError {
    static int j = 0;
    static int num=1;
    public static void main(String args[]){

        testStack();
    }

    public static  void testStack(){  //无出口的递归调用，栈溢出
        num++;
        testStack();
    }
}

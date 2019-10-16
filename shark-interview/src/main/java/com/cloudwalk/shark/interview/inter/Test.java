package com.cloudwalk.shark.interview.inter;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.inter
 * @date:2019/8/28
 */
public class Test {
    public static void main(String args[]){
        FatherInterface fatherInterface = new Father() {
            @Override
            public void say() {

            }
        };

        FatherInterface fatherInterface1 = new Son();


    }
}

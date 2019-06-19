package com.cloudwalk.shark.controller.interview.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Demo1 {

    public Integer count = 0;

    public static void main(String[] args) {
        final Demo1 demo1 = new Demo1();
        Executor executor = Executors.newFixedThreadPool(10);
        for(int i=0;i<1000;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    demo1.count++;
                }
            });
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("final count value:"+demo1.count);
    }
}

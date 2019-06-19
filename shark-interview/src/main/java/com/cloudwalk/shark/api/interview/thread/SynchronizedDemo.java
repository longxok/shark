package com.cloudwalk.shark.api.interview.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SynchronizedDemo {

    public  volatile  Integer count = 0;
    public static CountDownLatch countDownLatch = new CountDownLatch(1000);
    public static void main(String[] args) {
        final SynchronizedDemo demo = new SynchronizedDemo();
        final Object o = new Object();
        Executor executor = Executors.newFixedThreadPool(10);
        for(int i=0;i<1000;i++){
            executor.execute(new Runnable() {
                public void run() {
                    synchronized (o){
                        demo.count++;
                        countDownLatch.countDown();
                    }
                }
            });
        }
        try {
           countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("final count value:"+demo.count);
    }
}
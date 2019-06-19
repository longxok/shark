package com.cloudwalk.shark.api.interview.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest implements Runnable{
    private static CountDownLatch countDownLatch = new CountDownLatch(2);
    public static void main(String[] args){
     new Thread(new CountDownLatchTest()).start();
     new Thread(new CountDownLatchTest()).start();
        try {
            countDownLatch.await();
            System.out.println("Run over!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+"Run over!");
            Thread.sleep(3000);
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

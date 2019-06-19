package com.cloudwalk.shark.controller.interview.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    public volatile Integer count = 0;
    public static  CountDownLatch countDownLatch = new CountDownLatch(2000);
    public static Lock lock =  new ReentrantLock();
    public static void main(String[] args) {
        final LockDemo demo = new LockDemo();
        Executor executor = Executors.newFixedThreadPool(100);
        for(int i=0;i<2000;i++){
            executor.execute(new Runnable() {
                public void run() {
                    while(true) {
                        if (lock.tryLock()) {
                            try{
                                demo.count++;
                                countDownLatch.countDown();
                                break;
                            }catch(Exception e){
                            }finally {
                                lock.unlock();
                            }
                        }
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
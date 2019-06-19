package com.cloudwalk.shark.controller.interview.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierTest implements Runnable {
    private CyclicBarrier cyclicBarrier;
    private long waitTime;
    public static void main(String[] args){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName()+"haha");
            }
        });

        new Thread(new CyclicBarrierTest(cyclicBarrier,1000)).start();
        new Thread(new CyclicBarrierTest(cyclicBarrier,5000)).start();

    }
    public CyclicBarrierTest(CyclicBarrier cyclicBarrier,long waitTime){
        this.cyclicBarrier = cyclicBarrier;
        this.waitTime = waitTime;
    }
    public void run() {
        System.out.println("go here!");
        try {
            Thread.sleep(waitTime);
            cyclicBarrier.await(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"come here!");
    }
}

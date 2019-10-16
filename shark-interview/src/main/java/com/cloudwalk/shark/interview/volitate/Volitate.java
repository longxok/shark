package com.cloudwalk.shark.interview.volitate;

import java.util.concurrent.CountDownLatch;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.volitate
 * @date:2019/8/22
 */
public class Volitate implements Runnable{

    private static volatile int a;
    private static CountDownLatch  countDownLatch = new CountDownLatch(2);

    public static void main(String args[]){
        Thread t1 = new Thread(new Volitate());
        Thread t2 = new Thread(new Volitate());
        t1.start();
        t2.start();
        try {
            countDownLatch.await();
            System.out.println(Volitate.getA());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int getA(){
        return a;
    }
    @Override
    public void run() {
        for(int i=0;i<10000;i++) {
            a++;
        }
        countDownLatch.countDown();
    }
}

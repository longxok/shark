package com.cloudwalk.shark.interview.lock;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.lock
 * @date:2019/7/18
 */
public class TestLock implements Runnable{
    private static int initInt;
    /**
     * 一定要注意锁是对象锁还是类锁，也就是共享一把锁，还是每个对象一把锁
     * 这个一定要清楚，否则会失效，达不到效果
     */
    private static ReentrantLock lock = new ReentrantLock();



    @Override
    public void run() {
        lock.lock();
        initInt++;
        lock.unlock();
    }



    public static void main(String args[]) throws InterruptedException {

        ExecutorService executorService = new ThreadPoolExecutor(5,10,10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
        for(int i=0;i<500000;i++){
            TestLock lock = new TestLock();
            executorService.execute(lock);
        }
        Thread.sleep(5000);
        System.out.println(TestLock.initInt);
    }
}

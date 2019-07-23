package com.cloudwalk.shark.interview.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.thread.pool
 * @date:2019/7/5
 */
public class ThreadPoolTest {
    private static int i = 0;
    private static Object object = new Object();
    public static AtomicInteger threadIndex = new AtomicInteger();
    public static ThreadPoolExecutor executors = new ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(3), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("Thread" + threadIndex.getAndIncrement());
            return t;
        }
    }, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String args[]) {
        for (int i = 0; i < 10; i++) {
            RunClass thread = new RunClass();
            executors.execute(thread);
        }
    }

    static class RunClass implements Runnable {
        @Override
        public void run() {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
               /* synchronized (object){
                    i++;
                }*/
            System.out.println("thread name is " + Thread.currentThread().getName() + "&&" + i);

        }
    }

}

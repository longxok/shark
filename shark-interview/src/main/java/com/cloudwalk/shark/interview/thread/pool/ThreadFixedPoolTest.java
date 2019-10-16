package com.cloudwalk.shark.interview.thread.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.thread.pool
 * @date:2019/7/30
 */
public class ThreadFixedPoolTest implements Runnable {
    public static BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();

    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 10, 2, TimeUnit.SECONDS, queue, new ThreadFactory() {

        private AtomicInteger id = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread();
            thread.setName("core_service" + id.addAndGet(1));
            return thread;
        }
    }, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {


        for (int i = 0; i < 100; i++) {
            ThreadFixedPoolTest test = new ThreadFixedPoolTest();
            executor.execute(test);
            System.out.println("线程队列大小为-->" + queue.size());
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //executor.shutdown();
    }


    @Override
    public void run() {
        System.out.println("1111111111111");
    }
}

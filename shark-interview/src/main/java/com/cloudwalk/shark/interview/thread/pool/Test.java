package com.cloudwalk.shark.interview.thread.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.thread.pool
 * @date:2019/7/31
 */
public class Test implements Runnable {
    static BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();

    static BlockingQueue<Runnable> arrayQueue = new ArrayBlockingQueue<Runnable>(3);

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 10, 5, TimeUnit.SECONDS, arrayQueue, new ThreadFactory() {

        private AtomicInteger id = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("core_service" + id.addAndGet(1));
            return thread;
        }
    }, new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "11111111111111");
        System.out.println("**********取队列大小" + arrayQueue.size());
        executor.getActiveCount();
      /*  try {

            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public static void main(String args[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);


        for (int i = 0; i < 100; i++) {

            Future future = executor.submit(new Thread(new Test()));
            System.out.println("队列大小" + arrayQueue.size());
        }
    }
}

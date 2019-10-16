package com.cloudwalk.shark.interview.thread.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.thread.pool
 * @date:2019/7/30
 */
public class ThreadPoolExecutorTest {
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(2,100,2L, TimeUnit.MINUTES,new LinkedBlockingQueue<>(),new ThreadFactory(){

        private AtomicInteger id = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r){
            Thread thread = new Thread();
            thread.setName("core_service"+id.addAndGet(1));
            return thread;
        }
    },new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args)throws Exception{

        Future<Long> f1 = (Future<Long>) executor.submit(()->{

            Thread.sleep(1000);
            Future<Long> f3 = (Future<Long>) executor.submit(()->{return -1L;});

            System.out.println("f1,f3"+f3.get());
            return -1L;
        });


        Future<Long> f2 = (Future<Long>) executor.submit(()->{

            Thread.sleep(1000);
            Future<Long> f4 = (Future<Long>) executor.submit(()->{return -1L;});

            System.out.println("f2,f4"+f4.get());
            return -1L;
        });

        System.out.println("here");
        System.out.println("f1"+f1.get());
        System.out.println("f2"+f2.get());


    }
}

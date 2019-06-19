package com.cloudwalk.shark.api.portal.classload.test;

import java.util.concurrent.*;

public class TestThreadPool {
    public static void main(String[] args){
        method1();
    }



    public static void method1() {
        BlockingQueue queue = new LinkedBlockingQueue();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 1, TimeUnit.SECONDS, queue);
       // executor.allowCoreThreadTimeOut(true);
        for ( int i = 0; i < 20; i++) {
            executor.execute( new Runnable() {
                @Override
                public void run() {
                    try {
                        System. out.println( this.hashCode()/1000);
                        for ( int j = 0; j < 10; j++) {
                            System. out.println( this.hashCode() + ":" + j);
                            Thread. sleep(this.hashCode()%2);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System. out.println(String. format("thread %d finished", this.hashCode()));
                }
            });
        }
    }
}

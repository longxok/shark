package com.cloudwalk.shark.interview.thread.pool;



import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.thread.pool
 * @date:2019/7/11
 */
@Slf4j
public class ThreadExceptionTest {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService execute = new ThreadPoolExecutor(5, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        ((ThreadPoolExecutor) execute).allowsCoreThreadTimeOut()
        execute.execute(new Runnable() {
            @Override
            public void run() {
                log.info("=====11=======");
                System.out.println("=====11=======");
            }
        });

        TimeUnit.SECONDS.sleep(5);


        execute.execute(new Run1());

        TimeUnit.SECONDS.sleep(5);

        execute.execute(new Run2());
        execute.shutdown();

    }


    private static class Run1 implements Runnable {

        @Override
        public void run() {
            int count = 0;
            while (true) {
                count++;
               // log.info("-------222-------------{}", count);
                System.out.println("-------222-------------{}"+count);
                if (count == 10) {
                    System.out.println(1 / 0);
                   /* try {
                    } catch (Exception e) {
                     //   LOGGER.error("Exception",e);
                    }*/
                }

                if (count == 20) {
                    log.info("count={}", count);
                    break;
                }
            }
        }
    }

    private static class Run2 implements Runnable {

        public Run2() {
            log.info("run2 构造函数");
            System.out.println("run2 构造函数");
        }

        @Override
        public void run() {
            log.info("run222222222");
            System.out.println("run222222222");
        }
    }
}

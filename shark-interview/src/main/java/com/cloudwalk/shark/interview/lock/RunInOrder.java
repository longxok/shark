package com.cloudwalk.shark.interview.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.lock
 * @date:2019/11/14
 */
public class RunInOrder {
    public static Lock lock = new ReentrantLock(false);
    private static int state = 0;

    public static void main(String args[]) {
        new Thread(new A(), "thread-1:").start();
        new Thread(new B(), "thread-2:").start();

        System.out.println("111111");
    }

    static class A implements Runnable {
        @Override
        public void run() {
            while (state < 100) {
                lock.lock();
                if (state % 2 == 0) {
                    System.out.println(Thread.currentThread().getName()+state);
                    state++;
                }
                lock.unlock();
            }
        }
    }


    static class B implements Runnable {
        @Override
        public void run() {
            while (state < 100) {
                lock.lock();
                if (state % 2 != 0) {
                    System.out.println(Thread.currentThread().getName()+state);
                    state++;
                }
                lock.unlock();
            }
        }
    }

}


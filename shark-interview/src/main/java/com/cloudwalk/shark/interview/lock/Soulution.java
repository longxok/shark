package com.cloudwalk.shark.interview.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.lock
 * @date:2019/9/6
 */
public class Soulution {
        private int n;
        ReentrantLock lock = new ReentrantLock();
        Semaphore  semaphore = new Semaphore(1);
        public Soulution(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(int printNumber) throws InterruptedException {
            lock.lock();
            System.out.print("0");
            lock.unlock();
        }

        public void even(int printNumber) throws InterruptedException {

        }

        public void odd(int printNumber) throws InterruptedException {

        }

        public void printNumber(int num){
            for(int i=0;i<num;i++){

            }
        }
    }


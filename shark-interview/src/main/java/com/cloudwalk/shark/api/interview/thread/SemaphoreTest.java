package com.cloudwalk.shark.api.interview.thread;

import java.util.concurrent.Semaphore;

public class SemaphoreTest implements Runnable{
    private Semaphore semaphore;

    public SemaphoreTest(Semaphore semaphore){
        this.semaphore = semaphore;
    }
    public void run() {
        while(true) {
            if (semaphore.tryAcquire()) {
                System.out.println(Thread.currentThread().getName() + "haha");
                try {
                    Thread.sleep(3000);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static void main(String[] args){
        Semaphore semaphore = new Semaphore(3);
        for(int i=0;i<10;i++){
            new Thread(new SemaphoreTest(semaphore)).start();
        }

    }
}

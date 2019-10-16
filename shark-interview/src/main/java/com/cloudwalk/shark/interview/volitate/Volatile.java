package com.cloudwalk.shark.interview.volitate;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.volitate
 * @date:2019/8/22
 */
public class Volatile implements Runnable{
    //自增变量i
    public volatile int i = 0;
    @Override
    public void run() {
        while (true){
            i++; //不断自增
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Volatile vt = new Volatile();
        Watcher watcher = new Watcher();
        watcher.v = vt;
        Thread t1 = new Thread(vt);
        Thread t2 = new Thread(watcher);
        t1.start();
        t2.start();
        Thread.sleep(10);
        //打印 i  和 s
        System.out.println("Volatile.i = " + vt.i + "\nwatcher.w  = " + watcher.monitor);
        System.exit(0);
    }
}
class Watcher implements Runnable{
    public  Volatile v;

    public  int monitor;
    @Override
    public void run() {
        while (true){
            monitor = v.i;//不断将v.i的值赋给s
        }
    }
}

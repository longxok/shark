package com.cloudwalk.shark.interview.lock;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.lock
 * @date:2019/7/23
 */
public class DeadLock implements Runnable {

    private int flagCurrent = -1;

    private static int flagThread1 = 0;

    private static int flagThread2 = 1;

    private static Object object1 = new Integer(1);

    private static Object object2 = new Double(2);

    @Override
    public void run() {

        System.out.println("CurrentThread" + flagCurrent);

        if (flagCurrent == flagThread1) {// 线程1进
            synchronized (object1) {// 线程1先锁定object1（期间要等500ms去锁定object2）
                try {
                    Thread.sleep(500);
                } catch (final Exception e) {
                    e.printStackTrace();
                }
                // 此时线程2已经锁定object2，需要【等待】线程2释放object2，然后线程1锁定object2释放object2，再释放object1
                // 死锁发生：线程1等待线程2释放object2，线程1才释放object1；线程2等待线程1释放object1，线程2才释放object2；
                synchronized (object2) {
                    System.out.println("1");
                }
            }
        }
        if (flagCurrent == flagThread2) {// 线程2进
            synchronized (object2) {// 线程2先锁定object2（期间要等500ms去锁定object1）
                try {
                    Thread.sleep(500);
                } catch (final Exception e) {
                    e.printStackTrace();
                }
                // 此时线程1已经锁定object1，需要【等待】线程1释放object1，然后线程2锁定object1释放object1，再释放object2
                // 死锁发生：线程1等待线程2释放object2，线程1才释放object1；线程2等待线程1释放object1，线程2才释放object2；
                synchronized (object1) {
                    System.out.println("0");
                }
            }
        }
    }

    public static void main(final String[] args) {
        final DeadLock runnableThread1 = new DeadLock();
        final DeadLock runnableThread2 = new DeadLock();

        runnableThread1.flagCurrent = flagThread1;
        runnableThread2.flagCurrent = flagThread2;

        new Thread(runnableThread1, "runnableThread1").start();
        new Thread(runnableThread2, "runnableThread2").start();

    }
}


package com. cloudwalk.shark.interview.oom;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.oom
 * @date:2019/7/10
 */
public class OOMTest {

    public static void main(String args[]) {

        /*if (args.length < 1) {
            System.out.println("please input thread numbers!");
            return;
        }*/
        (new Exception()).printStackTrace();
        int threadNumber = Integer.parseInt(args[0]);

        try {
            for (int i = 0; i < threadNumber; i++) {
                new Thread() {
                    public void run() {
                        while (true) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }.start();
                System.out.println("Thread " + i);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}

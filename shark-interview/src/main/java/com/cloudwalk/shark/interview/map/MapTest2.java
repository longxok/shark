package com.cloudwalk.shark.interview.map;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.map
 * @date:2019/10/10
 */


import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 前面文章提到Collections.synchronizedMap()与ConcurrentHashM两者都提供了线程同步的功能。
 * 那两者的区别在哪呢？我们们先来看到代码例子。
 * 下面代码实现一个线程对map进行写操作，另一个线程，读出并打印map数据。
 */
public class MapTest2 {

    private static Map<String, Object> map1 = new HashMap<String, Object>();
    private static Map<String, Object> map2 = new Hashtable<String, Object>();
    private static Map<String, Object> map3 = new ConcurrentHashMap<String, Object>();
    private static Map<String, Object> map4 = Collections.synchronizedMap(new HashMap<String, Object>());

    private static Map<String, Object> map = map3;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (map.size() > 0) {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            System.out.println(String.format("%s: %s", entry.getKey(), entry.getValue()));
                        }
                        map.clear();
                    }
                    try {
                        Thread.sleep((new Random().nextInt(10) + 1) * 1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {

                for (int i = 1; i <= 100; i++) {
                    map.put("key" + i, "value" + i);
                    try {
                        Thread.sleep((new Random().nextInt(10) + 1) * 1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}


package com.cloudwalk.shark.api.interview.unique;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TSS {

    // 默认1个大小
    private static HashMap<String, AtomicInteger> tssCache
            = new HashMap<String, AtomicInteger>(1);

    private static final ReentrantLock lock = new ReentrantLock();

    // 因为有锁，所以是变成了线程安全的，省去每次 new 的消耗，耗时降低约一半
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");

    public static String getTimeStampSequence() {
        String timestamp = null;
        String inc = null;
        lock.lock();
        try {
            timestamp = sdf.format(new Date());
            AtomicInteger value = tssCache.get(timestamp);
            if (value == null) {
                tssCache.clear();
                int defaultStartValue = 0;
                tssCache.put(timestamp, new AtomicInteger(defaultStartValue));
                inc = String.valueOf(defaultStartValue);
            } else {
                inc = String.valueOf(value.addAndGet(1));
            }
        } finally {
            lock.unlock();
        }

        return timestamp + StringUtils.leftPad(inc, 4, '0');
    }
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        System.out.println(TSS.getTimeStampSequence());
                    }
                }
            }).start();
        }
    }
}
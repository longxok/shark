package com.cloudwalk.shark.interview.lintCode;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.lintCode
 * @date:2019/9/5
 */
public class LRUCache {
    ArrayDeque<LRUCache> queue = new ArrayDeque<LRUCache>();
    private int key;
    private int value;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public LRUCache() {

    }

    public LRUCache(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int get(int key) {
       /* LRUCache cache = queue.peek();
        while (cache!=null) {
            if (cache.key == key) {
                queue.remove(cache);
                queue.add(cache);
                return cache.value;
            }
            cache = queue.clone();
        }*/
        return -1;
    }

    public void put(int key, int value) {
        if (queue.size() == capacity) {
            queue.pollFirst();
            queue.add(new LRUCache(key, value));
        } else {
            queue.add(new LRUCache(key, value));
        }
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public static void main(String args[]){
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(2));
        lruCache.put(1, 1);
        lruCache.put(4, 1);
        System.out.println(lruCache.get(2));

    }

    @Test
    public void testQueue() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}
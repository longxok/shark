package com.cloudwalk.shark.interview.env;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.env
 * @date:2019/7/23
 */
public enum PowerOfTen {
    ONE(1), TEN(10),
    HUNDRED(100);
    private final int val;

    PowerOfTen(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(ONE + " " + TEN + " " + HUNDRED);
    }
}

package com.cloudwalk.shark.interview.classload.test;

import com.cloudwalk.shark.interview.classload.Car;

public class TestClassLoad {
    public static void main(String[] args){
        try {
          /*  ClassLoader loader = Thread.currentThread().getContextClassLoader();
            System.out.println(loader);
            Class<?> carClass1 = Thread.currentThread().getContextClassLoader().loadClass("com.cloudwalk.shark.interview.classload.Car");
            Car car2 =(Car) carClass1.newInstance();*/


            Class<?> carClass = Class.forName("com.cloudwalk.shark.interview.classload.Car");
            Car car = (Car)carClass.newInstance();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}

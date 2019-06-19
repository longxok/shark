package com.cloudwalk.shark.controller.portal.classload;

public class Car {
    static{
        System.out.println("This is static block");
    }
    private String name;
    private String age;

    public Car(){
        System.out.println("This is constractor");
    }
}

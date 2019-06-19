package com.cloudwalk.shark.api.interview.reflect;

import java.lang.reflect.Method;

public class ReflectClass {

    public static void main(String[] args){
        Method[] methods = RelfectChiled.class.getMethods();
        for(Method method:methods){
            System.out.println(String.format("%s 方法名,declareClase %s ", method.getName(),method.getDeclaringClass().getName()));
        }
    }

    class RelfectChiled extends TestRelfectFather{
        @Override
        public String getInfo(){
            return "22222";
        }
    }

    class TestRelfectFather{
        public String getInfo(){
            return "111111";
        }
    }
}

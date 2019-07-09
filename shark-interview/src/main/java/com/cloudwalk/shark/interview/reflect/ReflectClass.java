package com.cloudwalk.shark.interview.reflect;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectClass {

    public static void main(String[] args){
       /* Method[] methods = RelfectChiled.class.getMethods();
        for(Method method:methods){
            System.out.println(String.format("%s 方法名,declareClase %s ", method.getName(),method.getDeclaringClass().getName()));
        }*/
        ReflectionUtils.doWithMethods(TestRelfectFather.class,new ReflectionUtils.MethodCallback(){

            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {

                System.out.println(method.getName()+"1111111111");
            }
        },new ReflectionUtils.MethodFilter(){

            @Override
            public boolean matches(Method method) {
                if(method.getName().equals("getInfo")){
                    return true;
                }
                return false;
            }
        });
        Field field = ReflectionUtils.findField(TestRelfectFather.class,"name");
        if(field == null){

        }else{
            System.out.println("field name is "+field.getName());
        }
    }

    class RelfectChiled extends TestRelfectFather{
        @Override
        public String getInfo(){
            return "22222";
        }
    }

    class TestRelfectFather{
        private String name;
        public String getInfo(){
            return "111111";
        }
    }
}

package com.cloudwalk.shark.interview.jvm;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.jvm
 * @date:2019/7/18
 */
public class JvmObject {

    private int i = 1;

    private PropertyObject propertyObject = new PropertyObject("");
    private PropertyObject propertyObject2 = new PropertyObject("456");

    private int addInt(int num){
        return ++i;
    }

    private void setProperty(PropertyObject propertyObject){
        this.propertyObject.setName("123");
        this.propertyObject = new PropertyObject("456");
    }


    public PropertyObject getPropertyObject() {
        return propertyObject;
    }

    public void setPropertyObject(PropertyObject propertyObject) {
        this.propertyObject = propertyObject;
    }

    private void print(){
        System.out.printf("value is %s,name is %s",i,getPropertyObject().getName());

    }

    public static void main(String args[]){
        JvmObject jvmObject = new JvmObject();
        jvmObject.addInt(3);
        jvmObject.setProperty(jvmObject.getPropertyObject());
        jvmObject.print();

      /*  String s1 = "ja";
        String s2 = "va";
        String s3 = "java";
        String s4 = s1 + s2;
        System.out.println(s3 == s4);//false
        System.out.println("ja"+"va" == "java");//false

        System.out.println(s3.equals(s4));//true*/
    }

    class PropertyObject{
        private String name;

        public PropertyObject(String name){
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

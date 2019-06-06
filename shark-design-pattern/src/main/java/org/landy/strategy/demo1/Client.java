package org.landy.strategy.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author landyl
 * @create 2:34 PM 05/12/2018
 */
public class Client {

    public static void main(String[] args) {
//        ConcreteStrategyA a = new ConcreteStrategyA();
//        ConcreteStrategyB b = new ConcreteStrategyB();
//
//        Context context = new Context(a);
//
//        context.contextMethod();

        //可使用工厂模式包装一下
        //StrategyFactory.strategyMethod("a");
        StudentInter studentInter = new Student();
        StudentInter student = (StudentInter)new ProxyOwn(studentInter).getInstance();
        student.sayHello();
    }

    static class ProxyOwn implements InvocationHandler{
        private Object taraget;

        public ProxyOwn(Object object){
            this.taraget = object;
        }

        public Object getInstance(){
            return Proxy.newProxyInstance(taraget.getClass().getClassLoader(),taraget.getClass().getInterfaces(),
                    new ProxyOwn(taraget));
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(taraget,args);
        }
    }

    static class Student implements StudentInter{
        private String name;
        private String age;
        @Override
        public void sayHello(){
            System.out.println("123");
        }
    }
}

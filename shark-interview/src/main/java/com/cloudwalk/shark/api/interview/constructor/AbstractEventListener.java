package com.cloudwalk.shark.api.interview.constructor;


/**
 * 用来测试Nacos中的监听事件实现，抽象类中有构造方法
 * 继承子类中的类都会先调用这个类，然后再执行子类中自己的逻辑
 */
public abstract class AbstractEventListener {
    public AbstractEventListener(){
        System.out.println("print abstract constractor init");
    }


    static class AsyncNotifyService extends AbstractEventListener{
        private String serviceName ;
        public AsyncNotifyService(String serviceName){
            System.out.println("print class AsyncNotifyService constractor init");
            this.serviceName = serviceName;
        }
    }

    static class LongPollingService extends AbstractEventListener {
        public LongPollingService() {
            System.out.println("print class LongPollingService constractor init");
        }
    }

    public static void main(String[] args){
        AsyncNotifyService asyncNotifyService = new AsyncNotifyService("123");
        LongPollingService longPollingService = new LongPollingService();
    }
}

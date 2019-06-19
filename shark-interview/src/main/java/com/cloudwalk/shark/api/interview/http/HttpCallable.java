package com.cloudwalk.shark.api.interview.http;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 异步执行HTTP请求
 */
public class HttpCallable implements Callable<String> {

    private String url;

    private String jsonParams;

    public HttpCallable(String url, String jsonParams){
        this.url = url;
        this.jsonParams = jsonParams;
    }

    /**
     * 执行并返回结果
     */
    public String call() throws Exception {
        return HttpTool.requestPost(url, jsonParams);
    }

    /**
     * 模拟并发测试
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //模拟并发数
        int concurrencyNumber = 10;

        List<String>  resList = Lists.newArrayList();

        //执行线程池
        ExecutorService ex = Executors.newFixedThreadPool(50);

        String url = "http://10.129.13.96:8083/lock/db/1111";
        Map<String, String> mapPatam = new HashMap<String, String>();
        mapPatam.put("name", "测试");


        for(int i =0 ; i< concurrencyNumber; i++){
            Future<String> callRes = ex.submit(new HttpCallable(url, JSON.toJSONString(mapPatam)));
            resList.add(i + ">>>" + callRes.get());
        }

        for(String s : resList){
            System.out.println("------>" + s);
        }
    }

}

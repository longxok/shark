package com.cloudwalk.shark.api.interview.map;

import java.util.Map;
import java.util.TreeMap;

public class SortMap {
    public static void main(String[] args){
        Map<String,Object> map = new TreeMap<String,Object>();
        map.put("b","11");
        map.put("a","11");

        map.put("aa","11");
       for(String key: map.keySet()){
           System.out.println(key);
       }
    }
}

package com.cloudwalk.shark.interview.map;

import java.util.*;

public class SortMap {
    public static void main(String[] args){
        Map<String,Object> map = new TreeMap<String,Object>();
        Map<String, String> linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);

        for(int i=0;i<100;i++){
            map.put(""+i,"i"+i);
        }


        for(Map.Entry entry: map.entrySet()){
            System.out.println("key:"+entry.getKey()+",hashCode:"+entry.hashCode());
        }



        linkedHashMap.put("11","a");
        linkedHashMap.put("22","b");
        linkedHashMap.put("33","b");

        linkedHashMap.get("22");
        map.put("aa","11");
       for(String key: map.keySet()){
           System.out.println(key);
       }

        for(String key: linkedHashMap.keySet()){
            System.out.println(key);
        }
        System.out.println("&&&&&&&&&&");
        Set<Map.Entry<String, String>> set2 = linkedHashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator2 = set2.iterator();
        while(iterator2.hasNext()) {
            Map.Entry entry = iterator2.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println("key:" + key + ",value:" + value);
        }
    }
}

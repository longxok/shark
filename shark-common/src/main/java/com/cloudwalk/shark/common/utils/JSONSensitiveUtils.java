package com.cloudwalk.shark.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloudwalk.shark.common.em.SensitiveEnum;
import org.codehaus.jettison.json.JSONTokener;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class JSONSensitiveUtils {

    public static final Set<SensitiveEnum> sensitiveMap = new HashSet<SensitiveEnum>();

    static {
        sensitiveMap.add(SensitiveEnum.BANK_NO);
        sensitiveMap.add(SensitiveEnum.PHONE);
        sensitiveMap.add(SensitiveEnum.EMAIL);
        sensitiveMap.add(SensitiveEnum.ID_CARD);
    }

    public static void main(String[] args) {
        String sa = "{'sa':'saas','sb':['sa','ds','sda'],'sc':{'dsa':'21'}}";
        JSONObject jsonObject = JSON.parseObject(sa);
        System.out.println(changeSensitiveMsg(jsonObject).toJSONString());
    }

    /**
     * @param jsonObject
     * @return
     */
    public static JSONObject changeSensitiveMsg(JSONObject jsonObject) {
        for (String key : jsonObject.keySet()) {
            String json = jsonObject.getString(key);
            if (isObject(json)) {
                jsonObject.put(key, changeSensitiveMsg(JSON.parseObject(json)));
            } else if (isArray(json)) {
                jsonObject.put(key, changeSensitiveMsg(JSON.parseArray(json)));
            } else {
                //这里才是最终覆盖敏感属性的操作
                jsonObject.put(key, isSensitiveKey(key,json));

            }
        }
        return jsonObject;
    }

    /**
     * @param jsonArray
     * @return
     */
    public static JSONArray changeSensitiveMsg(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            String value = jsonArray.getString(i);
            if (isArray(value)) {
                jsonArray.set(i, changeSensitiveMsg(jsonArray.getJSONArray(i)));
            } else if (isObject(value)) {
                jsonArray.set(i, changeSensitiveMsg(JSON.parseObject(value)));
            }
        }
        return jsonArray;
    }

    //判断是否是对象，这个方法需要优化，遇到特殊字符相当占时间，可以根据json串首字母直接判断
    public static boolean isObject(String str) {
        try {
            Object json = new JSONTokener(str).nextValue();
            if (json instanceof org.codehaus.jettison.json.JSONObject) {
                return true;
            }

        } catch (Exception e) {
            //return false;
        }
        return false;
    }

    //判断是否是数组，这个方法需要优化，遇到特殊字符相当占时间，可以根据json串首字母直接判断
    public static boolean isArray(String str) {
        try {
            Object json = new JSONTokener(str).nextValue();
            if (json instanceof org.codehaus.jettison.json.JSONArray) {
                return true;
            }
        } catch (Exception e) {
            //return false;
        }
        return false;
    }

    /**
     * 判断是否是敏感key,是的话调用工具类进行脱敏
     * @param key
     * @param json
     * @return
     */
    public static String isSensitiveKey(String key,String json) {
        Iterator<SensitiveEnum> iterator = sensitiveMap.iterator();
        while (iterator.hasNext()) {
            SensitiveEnum sensitiveEnum = iterator.next();
            String methodName = sensitiveEnum.getSensitiveMethod();
            if (key.equalsIgnoreCase(sensitiveEnum.getKeyName())) {

                /*    Method method = SensitiveDataUtil.class.getMethod(methodName,String.class);
                    return (String)method.invoke(null,json);*/
                return (String) ReflectUtil.callMethod(new SensitiveDataUtil(),methodName,json);

            }
        }
        return json;
    }
}


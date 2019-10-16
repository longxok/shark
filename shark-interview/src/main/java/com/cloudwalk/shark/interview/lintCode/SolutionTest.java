package com.cloudwalk.shark.interview.lintCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.lintCode
 * @date:2019/9/5
 */
class SolutionTest {
    public List<String> restoreIpAddresses(String s) {
        if(s.length()>12||s.length()<4) return null;
        List<List<Integer>> arrayList = new ArrayList<>();
        for(int i=1;i<4;i++){
            for(int j=1;j<4;j++){
                for(int k=1;k<4;k++){
                    for(int m=1;m<4;m++){
                        List<Integer> list = new ArrayList<>();
                        if(i+j+k+m==s.length()){
                            list.add(i);
                            list.add(j);
                            list.add(k);
                            list.add(m);
                            arrayList.add(list);
                        }
                    }
                }
            }
        }
        if(arrayList.size()==0) return null;
        List<String> listStr = new ArrayList<>();
        for(List<Integer> list:arrayList){
            int index =0;
            StringBuffer stringBuffer = new StringBuffer();
            boolean isStandard =true;
            for(Integer item:list){
                item+=index;
                String ipStr = s.substring(index,item);
                int ip = checkNum(ipStr);
                if(ip>255||ip<0){
                    isStandard =false;
                    break;
                }else{
                    index = item;
                    stringBuffer.append(ipStr).append(".");
                }

            }
            if(isStandard) {
                listStr.add(stringBuffer.toString().substring(stringBuffer.lastIndexOf("."),stringBuffer.length()));
            }
        }
        return listStr;
    }

    public int checkNum(String str){
        if(str.length()>1&&str.charAt(0)=='0') return -1;
            int num=0;
        char []  charArray = str.toCharArray();
        for(int i=0;i<str.length();i++){
            num =num*10+(charArray[i]-'0');
        }
        return num;
    }

    public static void main(String args[]){
        SolutionTest test = new SolutionTest();
        test.restoreIpAddresses("0000");
    }
}

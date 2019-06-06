package org.landy.adapter;

import java.io.*;

/**
 * Created by Landy on 2019/1/5.
 */
public class FileIODemo {

    public static void main(String[] args) throws Exception {
        //适配接口和被适配接口没有层次关系
        //目前拥有的对象
        InputStream is = new FileInputStream("D:\\abc.txt");
        //需要的对象
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"GBK"));

        print(reader);
    }

    private static void print(BufferedReader reader) throws IOException {
        String str ;
        while((str=reader.readLine())!=null){
            System.out.println(str);
        }
    }
}

package com.cloudwalk.shark.config.file;

import java.io.*;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.config.file
 * @date:2019/7/19
 */
public class FileTest {
    public static void main(String args[]){
        File file = new File("c:/1.txt");

        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file),"UTF-8");

            byte[] byteArray = new byte[1024];
            char[] charArray = new char[1024];
            while(inputStreamReader.read(charArray)!=-1){
                System.out.println(new String(charArray));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

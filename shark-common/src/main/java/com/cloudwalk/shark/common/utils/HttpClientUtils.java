package com.cloudwalk.shark.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.common.utils
 * @date:2019/6/26
 */
@Slf4j
public class HttpClientUtils {
        public static boolean post(String serverUrl, String data, long timeout) {
            StringBuilder responseBuilder = null;
            BufferedReader reader = null;
            OutputStreamWriter wr = null;

            URL url;
            try {
                url = new URL(serverUrl);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                conn.setConnectTimeout(1000 * 5);
                wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write(data);
                wr.flush();
/*
                if (logger.isDebugEnabled()) {
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    responseBuilder = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        responseBuilder.append(line).append("\n");
                    }
                    LOG.debug(responseBuilder.toString());
                }*/
            } catch (IOException e) {
               // LOG.error("", e);
            } finally {

                if (wr != null) {
                    try {
                        wr.close();
                    } catch (IOException e) {
                       // LOG.error("close error", e);
                    }
                }

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                      //  LOG.error("close error", e);
                    }
                }

            }

            return false;
        }

        public static void main(String args[]) {
            post("http://www.alibaba.com/trade/search", "fsb=y&IndexArea=product_en&CatId=&SearchText=test", 6000);
        }


    }

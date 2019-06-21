package com.cloudwalk.shark.common.poi.excel.write;

import com.cloudwalk.shark.common.poi.excel.model.WriteParam;

import java.io.OutputStream;

/**
 * @author kevin
 */
public interface WriteableExcel<T> {

    /**
     * 
     * @param param 写出的参数
     * @param out out
     */
    void write(WriteParam<T> param, OutputStream out);

}

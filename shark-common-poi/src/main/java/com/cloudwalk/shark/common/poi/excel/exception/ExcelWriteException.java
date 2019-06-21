package com.cloudwalk.shark.common.poi.excel.exception;


import com.cloudwalk.shark.common.poi.WriteException;

/**
 * 写入Excel异常
 * @author kevin
 */
@SuppressWarnings("serial")
public class ExcelWriteException extends WriteException {

    public ExcelWriteException(String message) {
        super(message);
    }

    public ExcelWriteException(String message, Throwable t) {
        super(message, t);
    }

}

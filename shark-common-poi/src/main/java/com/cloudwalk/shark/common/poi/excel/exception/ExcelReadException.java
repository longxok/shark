package com.cloudwalk.shark.common.poi.excel.exception;


import com.cloudwalk.shark.common.poi.ReadException;

/**
 * 读取Excel异常
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class ExcelReadException extends ReadException {

	public ExcelReadException(String message) {
		super(message);
	}

	public ExcelReadException(String message, Throwable t) {
		super(message, t);
	}

}

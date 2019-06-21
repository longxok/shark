package com.cloudwalk.shark.common.poi.excel.write;

import com.cloudwalk.shark.common.poi.excel.write.handler.SimpleWriteableHandler;
import com.cloudwalk.shark.common.poi.excel.write.handler.WriteableHandler;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author kevin
 *
 */
public final class XSSFWriteableExcel<T> extends AbstractWriteableExcel<T> {

	public XSSFWriteableExcel() {
		super(new SimpleWriteableHandler<>());
	}

	public XSSFWriteableExcel(WriteableHandler<T> writeHandler) {
		super(writeHandler);
	}

	@Override
	protected Workbook createWorkbook() {
		return new XSSFWorkbook();
	}

}

package com.cloudwalk.shark.common.poi.excel.write.handler;

import com.cloudwalk.shark.common.util.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.WorkbookUtil;

/**
 * 写入单个工作表数据
 * 
 * @author kevin
 *
 */
public class SimpleWriteableHandler<T> extends AbstractWriteableHandler<T> {

	@Override
	protected void writeWorkbook() {
		Sheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName(params.getSheetName()));
		if (StringUtils.isNotEmpty(params.getPassword())) {
			sheet.protectSheet(params.getPassword());
		}
		sheet.setDisplayGridlines(params.isDisplayGridLines());
		writeSheet(sheet);

	}

	protected void writeSheet(Sheet sheet) {
		createTitleRow(sheet);
		createDataRows(sheet, params.getData());
	}

}

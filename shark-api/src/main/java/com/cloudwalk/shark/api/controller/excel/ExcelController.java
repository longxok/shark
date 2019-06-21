package com.cloudwalk.shark.api.controller.excel;

import com.cloudwalk.shark.api.controller.excel.dto.ExcelVo;
import com.cloudwalk.shark.common.poi.excel.model.ReadParam;
import com.cloudwalk.shark.common.poi.excel.model.ReadResult;
import com.cloudwalk.shark.common.poi.excel.read.DomReadExcel;
import com.cloudwalk.shark.common.poi.excel.read.ReadableExcel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.api.controller.excel
 * @date:2019/6/20
 */
@Controller
public class ExcelController {
    @GetMapping("/excel")
    @ResponseBody
    public Object excelParser(){
        ReadParam<ExcelVo> readParam = ReadParam.<ExcelVo>builder().beanClazz(ExcelVo.class).build();
        ReadableExcel<ExcelVo> readableExcel = new DomReadExcel<>(readParam);
        ReadResult<ExcelVo> readResult = readableExcel.read(new File("C:/Users/YCKJ1733/Desktop/excel.xlsx"));
        return readResult;
    }
}

package com.cloudwalk.shark.controller;

import com.cloudwalk.shark.common.utils.ResponseData;
import com.cloudwalk.shark.dto.validate.AdvertQueryForm;
import com.cloudwalk.shark.editor.SharkDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/validate", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class SharkValidationController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("advert.");
        binder.registerCustomEditor(Date.class,"startDate",new SharkDateEditor());
    }


    @PostMapping("/bean")
    @ResponseBody
    public ResponseData checkBeanIsValid(@Validated @RequestBody AdvertQueryForm advertQueryForm){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM-dd");
        System.out.println(simpleDateFormat.format(advertQueryForm.getStartDate()));
        return new ResponseData(true,"2","3",advertQueryForm);
    }

    @PostMapping("/beanForm")
    @ResponseBody
    public ResponseData checkBeanIsValidFormUrlEncoded(AdvertQueryForm advertQueryForm){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM-dd");
        System.out.println(simpleDateFormat.format(advertQueryForm.getStartDate()));
        return new ResponseData(true,"2","3",advertQueryForm);
    }

}

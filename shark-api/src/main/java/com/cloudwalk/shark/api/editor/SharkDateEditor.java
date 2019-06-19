package com.cloudwalk.shark.api.editor;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SharkDateEditor extends PropertyEditorSupport {
    @Override
    public String getAsText() {
        //获取属性值
        Date date = (Date) getValue();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMM--dd");
        String str = dateFormat.format(date);
        String mydate = str.substring(0, 4) + str.substring(4, 6) + "--" + str.substring(8, 10);
        System.out.println(mydate);
        return mydate;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMM--dd");
        try {
            System.out.println(dateFormat.parse(text));
            //设置属性值
            setValue(dateFormat.parse(text));
        } catch (ParseException e) {
            System.out.println("ParseException....................");
        }
    }
}

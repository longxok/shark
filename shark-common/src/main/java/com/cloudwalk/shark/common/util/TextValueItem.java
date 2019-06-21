package com.cloudwalk.shark.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextValueItem implements Serializable {

    private String text;

    private Object value;

}

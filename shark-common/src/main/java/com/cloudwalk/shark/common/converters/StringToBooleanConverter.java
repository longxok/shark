package com.cloudwalk.shark.common.converters;


import com.cloudwalk.shark.common.util.BooleanUtils;

/**
 * String 转换为Boolean 类型
 *
 * @author kevin
 * @date 2017年9月1日上午11:29:54
 */
public class StringToBooleanConverter extends StringGenericConverter<Boolean> {

	/**
	 * 默认为null 时转换为 false
	 */
	public StringToBooleanConverter() {
		super(Boolean.FALSE, Boolean.class);
	}

	/**
	 *
	 */
	@Override
	protected Boolean doConvert(String source) {
		return BooleanUtils.toBoolean(source);
	}

}

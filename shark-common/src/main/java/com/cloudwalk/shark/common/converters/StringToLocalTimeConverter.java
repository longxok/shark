package com.cloudwalk.shark.common.converters;

import com.cloudwalk.shark.common.util.date.DatePattern;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalTimeConverter extends StringGenericConverter<LocalTime> {

	public StringToLocalTimeConverter() {
		super(LocalTime.class);
	}

	@Override
	protected LocalTime doConvert(String source) {
		return LocalTime.parse(source, DateTimeFormatter.ofPattern(DatePattern.HH_MM.getPattern()));
	}

}

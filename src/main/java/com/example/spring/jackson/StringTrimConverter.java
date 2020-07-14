package com.example.spring.jackson;

import org.apache.commons.lang3.*;

import com.fasterxml.jackson.databind.util.*;

public class StringTrimConverter
	extends StdConverter<String, String> {

	@Override
	public String convert(String value) {
		return StringUtils.defaultIfBlank(value, null);
	}

}

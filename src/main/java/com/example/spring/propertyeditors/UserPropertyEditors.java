package com.example.spring.propertyeditors;

import java.beans.*;

import org.apache.commons.lang3.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.spring.entity.*;
import com.example.spring.service.*;

@Component
public class UserPropertyEditors extends PropertyEditorSupport {

	@Autowired
	UserService service;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isNotEmpty(text))
			setValue(service.findById(text));
	}

	@Override
	public String getAsText() {
		if (getValue() instanceof User) {
			return ((User) getValue()).getId();
		}
		return "";
	}
}

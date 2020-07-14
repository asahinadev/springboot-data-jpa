package com.example.spring.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.beans.propertyeditors.*;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.*;

import com.example.spring.entity.*;
import com.example.spring.propertyeditors.*;

import lombok.extern.slf4j.*;
import reactor.core.publisher.*;

@Slf4j
@ControllerAdvice
public class AppControllerAdvice {

	@Autowired
	UserPropertyEditors userPropertyEditors;

	@Autowired
	RolePropertyEditors rolePropertyEditors;

	@Autowired
	GenderPropertyEditors genderPropertyEditors;

	@InitBinder
	public void initBuilder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(User.class, userPropertyEditors);
		binder.registerCustomEditor(Role.class, rolePropertyEditors);
		binder.registerCustomEditor(Gender.class, genderPropertyEditors);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WebExchangeBindException.class)
	public Mono<Void> execption(WebExchangeBindException exception) {
		if (log.isDebugEnabled()) {
			exception.getAllErrors().forEach(error -> {
				log.debug("{} {}", error.getObjectName(), error.getDefaultMessage());
			});
		}
		return null;
	}

}

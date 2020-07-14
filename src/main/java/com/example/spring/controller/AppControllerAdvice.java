package com.example.spring.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.beans.propertyeditors.*;
import org.springframework.http.*;
import org.springframework.validation.*;
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

	@ResponseBody
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WebExchangeBindException.class)
	public Flux<ObjectError> exceptionHandler(WebExchangeBindException exception) {
		log.warn("エラー件数 {}", exception.getErrorCount());
		if (log.isDebugEnabled()) {
			exception.getAllErrors().forEach(this::log);
		}
		return Flux.fromIterable(exception.getAllErrors());
	}

	private void log(ObjectError e) {
		log.debug("{}:{}", e.getObjectName(), e.getDefaultMessage());
	}

}

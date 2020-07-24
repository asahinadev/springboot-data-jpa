package com.example.spring.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.example.spring.entity.*;
import com.example.spring.form.*;
import com.example.spring.service.*;

@Controller
@RequestMapping("/gender")
public class GenderController
	extends CrudController<Gender, String, GenderForm, GenderService> {

	@Autowired
	public GenderController(GenderService service) {
		super(service, "/gender");
	}

	@Override
	protected void copy(GenderForm form, Gender entity) {
		entity.setCode(form.getCode());
		entity.setName(form.getName());
	}

	@Override
	protected Gender create(GenderForm form) {
		Gender entity = new Gender();
		entity.setCode(form.getCode());
		entity.setName(form.getName());
		return entity;
	}

	@Override
	protected void copy(Gender entity, GenderForm form) {
		form.setCode(entity.getCode());
		form.setName(entity.getName());
	}
}

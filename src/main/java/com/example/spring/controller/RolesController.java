package com.example.spring.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.example.spring.entity.*;
import com.example.spring.form.*;
import com.example.spring.service.*;

@Controller
@RequestMapping("/role")
public class RolesController
	extends CrudController<Role, String, RoleForm, RoleService> {

	@Autowired
	public RolesController(RoleService service) {
		super(service, "role");
	}

	@Override
	protected void copy(RoleForm form, Role entity) {
		entity.setCode(form.getCode());
		entity.setName(form.getName());
	}

	@Override
	protected Role create(RoleForm form) {
		Role entity = new Role();
		entity.setCode(form.getCode());
		entity.setName(form.getName());
		return entity;
	}

	@Override
	protected void copy(Role entity, RoleForm form) {
		form.setCode(entity.getCode());
		form.setName(entity.getName());
	}
}

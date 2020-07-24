package com.example.spring.controller;

import org.apache.commons.lang3.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.example.spring.entity.*;
import com.example.spring.form.*;
import com.example.spring.service.*;

@Controller
@RequestMapping("/user")
public class UsersController
	extends CrudController<User, String, UserForm, UserService> {

	protected final PasswordEncoder encoder;

	@Autowired
	public UsersController(UserService service, PasswordEncoder encoder) {
		super(service, "user");
		this.encoder = encoder;
	}

	@Override
	protected void copy(UserForm form, User entity) {
		entity.setUsername(form.getUsername());
		entity.setEmail(form.getEmail());
		if (StringUtils.isNotEmpty(form.getPassword())) {
			entity.setPassword(encoder.encode(form.getPassword()));
		}
		entity.setEnabled(true);
		entity.setLocked(false);
	}

	@Override
	protected User create(UserForm form) {
		User entity = new User();
		entity.setUsername(form.getUsername());
		entity.setEmail(form.getEmail());
		entity.setPassword(encoder.encode(form.getPassword()));
		entity.setEnabled(true);
		entity.setLocked(false);
		return entity;
	}

	@Override
	protected void copy(User entity, UserForm form) {
		form.setUsername(entity.getUsername());
		form.setEmail(entity.getEmail());
	}
}

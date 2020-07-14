package com.example.spring.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.example.spring.entity.*;
import com.example.spring.form.*;
import com.example.spring.service.*;
import com.example.spring.validation.group.*;

import reactor.core.publisher.*;

@Controller
@RequestMapping("/signup")
public class SignupController {

	@Autowired
	protected UserService service;

	@GetMapping("")
	public String add(@ModelAttribute("form") UserForm form) {
		return "/signup/index";
	}

	@PostMapping("")
	public Mono<User> addPost(
			@Validated(Create.class) @RequestBody UserForm form) {
		return Mono.create(e -> {
			e.success(service.insert(
					User.builder()
							.username(form.getUsername())
							.email(form.getEmail())
							.password(form.getPassword())
							.enabled(true)
							.locked(false)
							.build()));
		});
	}

}

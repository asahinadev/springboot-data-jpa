package com.example.spring.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.example.spring.entity.*;
import com.example.spring.form.*;
import com.example.spring.service.*;
import com.example.spring.validation.group.*;

import lombok.extern.slf4j.*;
import reactor.core.publisher.*;

@Slf4j
@Controller
@RequestMapping("/signup")
public class SignupController {

	protected final UserService service;
	protected final PasswordEncoder encoder;

	@Autowired
	public SignupController(UserService service, PasswordEncoder encoder) {
		this.service = service;
		this.encoder = encoder;
	}

	@GetMapping("")
	public String get(@ModelAttribute("form") UserForm form) {
		log.debug("form => {}", form);
		return "/signup/index";
	}

	@ResponseBody
	@PostMapping("")
	@Validated(Create.class)
	public Mono<User> post(@RequestBody UserForm form) {
		log.debug("form => {}", form);

		User entity = new User();
		entity.setUsername(form.getUsername());
		entity.setEmail(form.getEmail());
		entity.setPassword(encoder.encode(form.getPassword()));
		entity.setEnabled(true);
		entity.setLocked(false);

		return Mono.just(service.insert(entity));
	}
}

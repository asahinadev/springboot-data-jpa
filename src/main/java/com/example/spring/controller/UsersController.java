package com.example.spring.controller;

import java.util.*;

import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
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
@RequestMapping("/user")
public class UsersController {

	@Autowired
	protected UserService service;

	@Autowired
	protected UserService roles;

	@ModelAttribute("roles")
	public List<User> roles() {
		return roles.findAll();
	}

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("users", service.findAll());
		return "/user/index";
	}

	@GetMapping("add")
	public String add(@ModelAttribute("form") UserForm form) {
		log.debug("form => {}", form);
		return "/user/form";
	}

	@GetMapping("{id}")
	public String update(
			@ModelAttribute("user") @PathVariable("id") User user,
			@ModelAttribute("form") UserForm form) {
		BeanUtils.copyProperties(user, form);
		log.debug("form => {}", form);
		return "/user/form";
	}

	@ResponseBody
	@PostMapping("add")
	public Mono<User> addPost(
			@Validated(Create.class) @RequestBody UserForm form) {
		log.debug("form => {}", form);
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

	@ResponseBody
	@PostMapping("{id}")
	public Mono<User> updatePost(
			@PathVariable("id") User user,
			@Validated(Save.class) @RequestBody UserForm form) {
		log.debug("form => {}", form);
		return Mono.create(e -> {
			user.setUsername(form.getUsername());
			user.setEmail(form.getEmail());
			user.setPassword(form.getPassword());
			user.setEnabled(form.isEnabled());
			user.setLocked(form.isLocked());
			e.success(service.update(user));
		});
	}

	@ResponseBody
	@DeleteMapping("{id}")
	public Mono<Void> delete(@PathVariable("id") User user) {
		log.debug("form => {}", user);
		return Mono.create(e -> {
			service.delete(user);
		});
	}
}

package com.example.spring.controller;

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
@RequestMapping("/role")
public class RolesController {

	@Autowired
	protected RoleService service;

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("roles", service.findAll());
		return "/role/index";
	}

	@GetMapping("add")
	public String add(@ModelAttribute("form") RoleForm form) {
		log.debug("form => {}", form);
		return "/role/form";
	}

	@GetMapping("{id}")
	public String update(
			@PathVariable("id") Role role,
			@ModelAttribute("form") RoleForm form) {
		BeanUtils.copyProperties(role, form);
		log.debug("form => {}", form);
		return "/role/form";
	}

	@ResponseBody
	@PostMapping("add")
	public Mono<Role> addPost(
			@Validated(Create.class) @RequestBody RoleForm form) {
		log.debug("form => {}", form);
		return Mono.create(e -> {
			e.success(service.insert(
					Role.builder()
							.code(form.getCode())
							.name(form.getName())
							.build()));
		});
	}

	@ResponseBody
	@PostMapping("{id}")
	public Mono<Role> updatePost(
			@PathVariable("id") Role role,
			@Validated(Save.class) @RequestBody RoleForm form) {
		log.debug("form => {}", form);
		return Mono.create(e -> {
			role.setCode(form.getCode());
			role.setName(form.getName());
			e.success(service.update(role));
		});
	}

	@ResponseBody
	@DeleteMapping("{id}")
	public Mono<Void> delete(@PathVariable("id") Role role) {
		log.debug("form => {}", role);
		service.delete(role);
		return null;
	}
}

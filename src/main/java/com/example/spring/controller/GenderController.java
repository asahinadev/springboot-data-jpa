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
@RequestMapping("/gender")
public class GenderController {

	@Autowired
	protected GenderService service;

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("genders", service.findAll());
		return "/gender/index";
	}

	@GetMapping("add")
	public String add(@ModelAttribute("form") GenderForm form) {
		log.debug("form => {}", form);
		return "/gender/form";
	}

	@GetMapping("{id}")
	public String update(
			@PathVariable("id") Gender gender,
			@ModelAttribute("form") GenderForm form) {
		BeanUtils.copyProperties(gender, form);
		log.debug("form => {}", form);
		return "/gender/form";
	}

	@ResponseBody
	@PostMapping("add")
	public Mono<Gender> addPost(
			@Validated(Create.class) @RequestBody GenderForm form) {
		log.debug("form => {}", form);
		return Mono.create(e -> {
			e.success(service.insert(
					Gender.builder()
							.code(form.getCode())
							.name(form.getName())
							.build()));
		});
	}

	@ResponseBody
	@PostMapping("{id}")
	public Mono<Gender> updatePost(
			@PathVariable("id") Gender gender,
			@Validated(Save.class) @RequestBody GenderForm form) {
		log.debug("form => {}", form);
		return Mono.create(e -> {
			gender.setCode(form.getCode());
			gender.setName(form.getName());
			e.success(service.update(gender));
		});
	}

	@ResponseBody
	@DeleteMapping("{id}")
	public Mono<Void> delete(@PathVariable("id") Gender gender) {
		log.debug("form => {}", gender);
		service.delete(gender);
		return null;
	}
}

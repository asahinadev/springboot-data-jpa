package com.example.spring.controller;

import java.io.*;

import org.springframework.data.jpa.repository.*;
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
public abstract class CrudController<
// @formatter:off
ENTITY  extends CrudEntity, 
ID      extends Serializable, 
FORM    extends CrudForm, 
SERVICE extends CrudService<ENTITY, ID, ? extends JpaRepository<ENTITY, ID>>
//@formatter:on
> {

	protected final SERVICE service;
	protected final String prefix;

	public CrudController(SERVICE service, String prefix) {
		this.service = service;
		this.prefix = prefix;
	}

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("list", service.findAll());
		return String.format("%s/index", prefix);
	}

	@GetMapping("add")
	public String add(@ModelAttribute("form") FORM form) {
		log.debug("form => {}", form);
		return String.format("%s/create", prefix);
	}

	@GetMapping("{id}")
	public String update(@PathVariable("id") ID id, @ModelAttribute("form") FORM form) {
		ENTITY entity = service.findById(id).get();
		copy(entity, form);
		log.debug("form => {}", form);
		return String.format("%s/modify", prefix);
	}

	@ResponseBody
	@PostMapping("add")
	@Validated(Create.class)
	public Mono<ENTITY> addPost(@RequestBody FORM form) {
		ENTITY saveEntity = create(form);
		log.debug("form => {}", form);
		log.debug("saveEntity => {}", saveEntity);
		return Mono.just(service.insert(saveEntity));

	}

	@ResponseBody
	@PostMapping("{id}")
	@Validated(Save.class)
	public Mono<ENTITY> updatePost(@PathVariable("id") ID id, @RequestBody FORM form) {
		ENTITY entity = service.findById(id).get();
		copy(form, entity);
		log.debug("form => {}", form);
		log.debug("saveEntity => {}", entity);
		return Mono.just(service.update(entity));
	}

	@ResponseBody
	@DeleteMapping("{id}")
	public Mono<Void> delete(@PathVariable("id") ID id) {
		ENTITY entity = service.findById(id).get();
		log.debug("entity => {}", entity);
		service.delete(entity);
		return null;
	}

	protected abstract void copy(FORM form, ENTITY entity);

	protected abstract ENTITY create(FORM form);

	protected abstract void copy(ENTITY entity, FORM form);

}

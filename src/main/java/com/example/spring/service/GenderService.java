package com.example.spring.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.spring.entity.*;
import com.example.spring.repository.*;

@Service
public class GenderService {

	@Autowired
	GenderRepository genderRepository;

	public List<Gender> findAll() {
		return genderRepository.findAll();
	}

	public Gender findById(String id) {
		return genderRepository.findById(id).orElseThrow();
	}

	public Gender findByCode(String code) {
		return genderRepository.findByCode(code).orElseThrow();
	}

	public Gender findByName(String name) {
		return genderRepository.findByName(name).orElseThrow();
	}

	public List<Gender> insert(Iterable<Gender> entities) {
		return genderRepository.saveAll(entities);
	}

	public Gender insert(Gender entity) {
		return genderRepository.save(entity);
	}

	public List<Gender> update(Iterable<Gender> entities) {
		return genderRepository.saveAll(entities);
	}

	public Gender update(Gender entity) {
		findById(entity.getId());
		return genderRepository.save(entity);
	}

	public void delete(Gender entity) {
		findById(entity.getId());
		genderRepository.delete(entity);
	}
}

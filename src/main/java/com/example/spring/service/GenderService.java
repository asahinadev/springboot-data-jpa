package com.example.spring.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.spring.entity.*;
import com.example.spring.repository.*;

@Service
public class GenderService extends CrudService<Gender, String, GenderRepository> {

	@Autowired
	public GenderService(GenderRepository genderRepository) {
		super(genderRepository);
	}

	public Gender findByCode(String code) {
		return repository.findByCode(code).orElseThrow();
	}

	public Gender findByName(String name) {
		return repository.findByName(name).orElseThrow();
	}

}

package com.example.spring.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.spring.entity.*;
import com.example.spring.repository.*;

@Service
public class RoleService extends CrudService<Role, String, RoleRepository> {

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		super(roleRepository);
	}

	public Optional<Role> findByCode(String code) {
		return repository.findByCode(code);
	}

	public Optional<Role> findByName(String name) {
		return repository.findByName(name);
	}

}

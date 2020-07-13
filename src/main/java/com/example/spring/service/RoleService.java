package com.example.spring.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.spring.entity.*;
import com.example.spring.repository.*;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	public Role findById(String id) {
		return roleRepository.findById(id).orElseThrow();
	}

	public Role findByCode(String code) {
		return roleRepository.findByCode(code).orElseThrow();
	}

	public Role findByName(String name) {
		return roleRepository.findByName(name).orElseThrow();
	}

	public List<Role> insert(Iterable<Role> entities) {
		for (Role entity : entities) {
			entity.setId(UUID.randomUUID().toString());
		}
		return roleRepository.saveAll(entities);
	}

	public Role insert(Role entity) {
		entity.setId(UUID.randomUUID().toString());
		return roleRepository.save(entity);
	}

	public List<Role> update(Iterable<Role> entities) {
		for (Role entity : entities) {
			entity.setId(UUID.randomUUID().toString());
		}
		return roleRepository.saveAll(entities);
	}

	public Role update(Role entity) {
		findById(entity.getId());
		return roleRepository.save(entity);
	}

	public void delete(Role entity) {
		findById(entity.getId());
		roleRepository.delete(entity);
	}
}

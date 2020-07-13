package com.example.spring.service;

import java.time.*;
import java.util.*;
import java.util.stream.*;

import org.apache.commons.lang3.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import com.example.spring.entity.User;
import com.example.spring.repository.*;

@Service
public class UserService
	implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		return userRepository.findById(id).orElseThrow();
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow();
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow();
	}

	public List<User> insert(Iterable<User> entities) {
		for (User entity : entities) {
			entity.setId(UUID.randomUUID().toString());
			changePassword(entity);
		}
		return userRepository.saveAll(entities);
	}

	public User insert(User entity) {
		entity.setId(UUID.randomUUID().toString());
		entity.setEnabled(true);
		changePassword(entity);
		return userRepository.save(entity);
	}

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		Objects.requireNonNull(username, "username is null");
		return userRepository.findByUsername(username)
				.orElse(userRepository.findByEmail(username).orElse(null));
	}

	public User update(User entity) {
		User old = findById(entity.getId());
		if (StringUtils.isEmpty(entity.getPassword())) {
			entity.setPassword(old.getPassword());
		} else if (!StringUtils.equals(entity.getPassword(), old.getPassword())) {
			changePassword(entity);
		}
		return userRepository.save(entity);
	}

	public List<User> update(Collection<User> entities) {
		return userRepository.saveAll(entities.stream().map(entity -> {
			User old = findById(entity.getId());
			if (!StringUtils.equals(entity.getPassword(), old.getPassword())) {
				changePassword(entity);
			}
			return entity;
		}).collect(Collectors.toList()));
	}

	public void delete(User entity) {
		findById(entity.getId());
		userRepository.delete(entity);
	}

	private void changePassword(User entity) {
		if (StringUtils.isNotEmpty(entity.getPassword())) {
			entity.setPassword(passwordEncoder.encode(entity.getPassword()));
			entity.setAccountExpired(LocalDateTime.now().plusDays(60));
			entity.setCredentialsExpired(LocalDateTime.now().plusDays(30));
		}
	}

}

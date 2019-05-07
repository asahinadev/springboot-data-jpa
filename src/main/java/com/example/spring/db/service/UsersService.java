package com.example.spring.db.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.spring.db.entity.User;
import com.example.spring.db.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsersService
		implements UserDetailsService {

	@Autowired
	UsersRepository usersRepository;

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		Assert.notNull(username, "Username not null");

		Optional<User> user;
		do {
			log.debug("{} {}", "findByUsername", username);
			user = usersRepository.findByUsername(username);
			if (user.isPresent()) {
				break;
			}

			log.debug("{} {}", "findByEmail", username);
			user = usersRepository.findByEmail(username);
			if (user.isPresent()) {
				break;
			}

			throw new UsernameNotFoundException("userdata not null : " + username);
		} while (false);
		return user.get();
	}

	public User insert(User user) {
		user.setId(UUID.randomUUID().toString());
		return usersRepository.save(user);
	}

	public User update(User user) {
		return usersRepository.save(user);
	}

	public User delete(User user) {
		user.setDeleted(LocalDateTime.now());
		return usersRepository.save(user);
	}

	public User findById(String id) throws NoSuchElementException {
		return usersRepository.findById(id).get();
	}

	public User findByUsername(String username) throws NoSuchElementException {
		return usersRepository.findByUsername(username).get();
	}

	public User findByEmail(String email) throws NoSuchElementException {
		return usersRepository.findByEmail(email).get();
	}

}

package com.example.spring.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import com.example.spring.entity.User;
import com.example.spring.repository.*;
import com.example.spring.user.*;

@Service
public class UserService
	extends CrudService<User, String, UserRepository>
	implements UserDetailsService {

	@Autowired
	public UserService(UserRepository userRepository) {
		super(userRepository);
	}

	public long countByUsername(String username) {
		return repository.countByUsername(username);
	}

	public long countByEmail(String email) {
		return repository.countByEmail(email);
	}

	public long countByUsername(String username, String ignoreId) {
		return repository.countByUsernameAndIdNot(username, ignoreId);
	}

	public long countByEmail(String email, String ignoreId) {
		return repository.countByEmailAndIdNot(email, ignoreId);
	}

	public Optional<User> findByUsername(String username) {
		return repository.findByUsername(username);
	}

	public Optional<User> findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Objects.requireNonNull(username, "username is null");

		if (repository.countByUsername(username) != 0) {
			return repository.findByUsername(username)
					.map(UserDetailsImpl::new).get();
		}

		if (repository.countByEmail(username) != 0) {
			return repository.findByEmail(username)
					.map(UserDetailsImpl::new).get();
		}

		throw new UsernameNotFoundException(username);
	}

}

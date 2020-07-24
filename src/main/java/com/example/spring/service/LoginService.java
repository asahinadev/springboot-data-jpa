package com.example.spring.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import reactor.core.publisher.*;

@Service
public class LoginService
	implements ReactiveUserDetailsService {

	@Autowired
	UserService service;

	@Override
	public Mono<UserDetails> findByUsername(String username) {
		return Mono.just(service.loadUserByUsername(username));
	}
}

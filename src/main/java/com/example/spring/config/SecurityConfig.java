package com.example.spring.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.reactive.*;
import org.springframework.security.config.web.server.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.server.*;

import com.example.spring.service.*;

@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		return http

				// 認証設定
				.authorizeExchange()

				// 認証不要
				.pathMatchers("/",
						"/login/**",
						"/signup/**",
						"/css/**",
						"/js/**",
						"/webjars/**",
						"/error/**",
						"/favicon.ico")
				.permitAll()

				// 認証必須
				.anyExchange().authenticated()
				.and()

				// FORM 認証
				.formLogin()
				.loginPage("/login")
				.and()

				// LOGOUT
				.logout()
				.logoutUrl("/login?logout")
				.and()

				// CSRF
				.csrf()
				.disable()

				// CORS
				.cors()
				.disable()

				// BASIC 認証
				.httpBasic()
				.disable()

				.build();
	}

	@Autowired
	UserService userService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

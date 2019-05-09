package com.example.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.spring.db.service.UsersService;

@EnableWebSecurity
public class SecurityConfig
		extends WebSecurityConfigurerAdapter {

	@Autowired
	UsersService usersService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.userDetailsService(usersService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.ignoring().antMatchers(
				"/**/*.js",
				"/**/*.css",
				"/**/*.map",
				"/**/*.jpg",
				"/**/*.gif",
				"/**/*.png",
				"/**/*.svg",
				"/favicon.ico");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/password/generator/**").permitAll()
				.antMatchers("/signin/**").permitAll()
				.anyRequest().authenticated()
				.and()

				.formLogin()
				.permitAll()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.failureUrl("/login?error=auth")
				.defaultSuccessUrl("/", true)
				.usernameParameter("username")
				.passwordParameter("password")
				.and()

				.rememberMe()
				.and()

				.httpBasic().disable()

				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout")
				.and()

		;

	}

}

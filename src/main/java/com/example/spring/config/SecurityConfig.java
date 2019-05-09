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

	public static final String LOGIN_URL = "/login";
	public static final String LOGOUT_URL = "/logout";
	public static final String SIGNIN_URL = "/signin";
	public static final String PASSWORD_GEN_URL = "/password/generator/**";

	public static final String PARAM_USERNAME = "username";
	public static final String PARAM_PASSWORD = "password";

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
				.antMatchers(PASSWORD_GEN_URL + "/**").permitAll()
				.antMatchers(SIGNIN_URL + "/**").permitAll()
				.antMatchers(LOGIN_URL + "/**").permitAll()
				.anyRequest().authenticated()
				.and()

				.formLogin().permitAll()

				.loginPage(LOGIN_URL)
				.loginProcessingUrl(LOGIN_URL)
				.failureUrl(LOGIN_URL + "?error=auth")
				.defaultSuccessUrl("/", true)

				.usernameParameter(PARAM_USERNAME)
				.passwordParameter(PARAM_PASSWORD)
				.and()

				.rememberMe()
				.and()

				.httpBasic().disable()

				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL))
				.logoutSuccessUrl(LOGIN_URL + "?logout")
				.and()

		;

	}

}

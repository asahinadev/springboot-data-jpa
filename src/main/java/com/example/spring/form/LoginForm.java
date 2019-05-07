package com.example.spring.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginForm {

	@NotEmpty
	String username;

	@NotEmpty
	String password;
}

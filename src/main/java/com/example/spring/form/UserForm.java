package com.example.spring.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserForm {

	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	@Length(min = 4, max = 20)
	String username;

	@NotEmpty
	@Email
	@Length(min = 1, max = 255)
	String email;

	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	@Length(min = 4, max = 16)
	String password;
}

package com.example.spring.form;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.example.spring.db.values.Actived;
import com.example.spring.db.values.Role;

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

	@NotEmpty
	@Size(min = 1)
	List<Role> roles;

	@NotNull
	Actived actived;

	@NotNull
	Actived locked;
}

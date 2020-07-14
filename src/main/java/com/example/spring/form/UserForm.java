package com.example.spring.form;

import java.time.*;
import java.util.*;

import javax.validation.constraints.*;

import com.example.spring.entity.*;
import com.example.spring.validation.*;
import com.example.spring.validation.group.*;

import lombok.*;

@Data
public class UserForm {

	@NotEmpty(groups = Save.class)
	@Code(groups = Save.class)
	String username;

	@NotEmpty(groups = Save.class)
	@Email(groups = Save.class)
	String email;

	@NotEmpty(groups = Create.class)
	@Password(groups = Save.class)
	String password;

	boolean enabled;

	boolean locked;

	LocalDateTime credentialsExpired;

	LocalDateTime accountExpired;

	List<Role> authorities;
}

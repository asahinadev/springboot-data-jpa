package com.example.spring.form;

import java.time.*;
import java.util.*;

import javax.validation.constraints.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.*;

import com.example.spring.entity.*;
import com.example.spring.validation.group.*;

import lombok.*;

@Data
public class UserForm {

	String id;

	@NotEmpty(groups = { Save.class, Create.class })
	@Pattern(regexp = "[\\w]+", groups = { Save.class, Create.class })
	@Length(min = 4, max = 16, groups = { Save.class, Create.class })
	String username;

	@NotEmpty(groups = Create.class)
	@Email(groups = { Save.class, Create.class })
	@Length(min = 4, max = 255, groups = { Save.class, Create.class })
	String email;

	@NotEmpty(groups = { Save.class, Create.class })
	@Pattern(regexp = "[\\w]+", groups = { Save.class, Create.class })
	@Length(min = 8, max = 16, groups = { Save.class, Create.class })
	String password;

	boolean enabled;

	boolean locked;

	LocalDateTime credentialsExpired;

	LocalDateTime accountExpired;

	List<Role> authorities;
}

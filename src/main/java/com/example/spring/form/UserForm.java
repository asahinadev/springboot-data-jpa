package com.example.spring.form;

import java.time.*;
import java.util.*;

import javax.validation.constraints.*;

import com.example.spring.entity.*;
import com.example.spring.jackson.*;
import com.example.spring.validation.*;
import com.example.spring.validation.group.*;
import com.fasterxml.jackson.databind.annotation.*;

import lombok.*;

@Data
public class UserForm {

	@NotEmpty(groups = Save.class)
	@Code(groups = Save.class)
	@JsonDeserialize(converter = StringTrimConverter.class)
	String username;

	@NotEmpty(groups = Save.class)
	@Email(groups = Save.class)
	@JsonDeserialize(converter = StringTrimConverter.class)
	String email;

	@NotEmpty(groups = Create.class)
	@Password(groups = Save.class)
	@JsonDeserialize(converter = StringTrimConverter.class)
	String password;

	boolean enabled;

	boolean locked;

	LocalDateTime credentialsExpired;

	LocalDateTime accountExpired;

	List<Role> authorities;
}

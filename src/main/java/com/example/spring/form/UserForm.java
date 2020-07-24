package com.example.spring.form;

import javax.validation.constraints.*;

import com.example.spring.jackson.*;
import com.example.spring.validation.*;
import com.example.spring.validation.group.*;
import com.fasterxml.jackson.databind.annotation.*;

import lombok.*;

@Data
public class UserForm implements CrudForm {

	@NotEmpty
	@Username
	@JsonDeserialize(converter = StringTrimConverter.class)
	String username;

	@NotEmpty
	@Email
	@JsonDeserialize(converter = StringTrimConverter.class)
	String email;

	@NotEmpty(groups = Create.class)
	@Password
	@JsonDeserialize(converter = StringTrimConverter.class)
	String password;

}

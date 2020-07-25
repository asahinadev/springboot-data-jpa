package com.example.spring.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.*;

import com.example.spring.validation.*;

import lombok.*;

@Data
public class RoleForm implements CrudForm {

	@NotEmpty
	@Code
	@Length(min = 4, max = 50)
	String code;

	@NotEmpty
	@Length(min = 4, max = 50)
	String name;

}

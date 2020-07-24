package com.example.spring.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.*;

import com.example.spring.validation.*;

import lombok.*;

@Data
public class RoleForm implements CrudForm {

	@NotEmpty
	@Code
	String code;

	@NotEmpty
	@Length(min = 8, max = 16)
	String name;

}

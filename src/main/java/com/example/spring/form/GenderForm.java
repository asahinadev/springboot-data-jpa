package com.example.spring.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.*;

import com.example.spring.validation.*;
import com.example.spring.validation.group.*;

import lombok.*;

@Data
public class GenderForm {

	@NotEmpty(groups = Save.class)
	@Code(groups = Save.class)
	String code;

	@NotEmpty(groups = Save.class)
	@Length(min = 8, max = 16, groups = Save.class)
	String name;

}

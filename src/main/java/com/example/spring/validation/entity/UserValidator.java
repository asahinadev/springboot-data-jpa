package com.example.spring.validation.entity;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;

import com.example.spring.entity.*;
import com.example.spring.service.*;

@Component
public class UserValidator implements Validator {

	@Autowired
	UserService users;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;

		long count;
		count = users.countByUsername(user.getUsername(), user.getId());
		if (count > 0) {
			errors.rejectValue("user.username", "0", null, "unique");
			return;
		}
		count = users.countByEmail(user.getEmail(), user.getId());
		if (count > 0) {
			errors.rejectValue("user.email", "0", null, "unique");
			return;
		}
	}

}

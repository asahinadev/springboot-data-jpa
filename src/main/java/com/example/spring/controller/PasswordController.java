package com.example.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.SessionAttributesHandler;

import com.example.spring.form.PasswordReminderForm;

@Controller("/password")
@RequestMapping
public class PasswordController
		extends AppController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@ResponseBody
	@GetMapping("generator/{password}")
	public String generator(@PathVariable("password") String password) {
		return passwordEncoder.encode(password);
	}

	@GetMapping(path = "reminder")
	public String reminderGet(
			SessionAttributesHandler a,
			@ModelAttribute("form") PasswordReminderForm form) {
		return "password/reminder";
	}

	public String reminderPost(@ModelAttribute("form") PasswordReminderForm form) {
		return "password/reminder";
	}
}

package com.example.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping
	public String index() {
		return "index";
	}

	@ResponseBody
	@GetMapping("password/generator/{password}")
	public String passwordGenerator(@PathVariable("password") String password) {
		return passwordEncoder.encode(password);
	}

}

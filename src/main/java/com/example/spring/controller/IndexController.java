package com.example.spring.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class IndexController {

	@GetMapping("")
	public String index() {
		return "index";
	}
}

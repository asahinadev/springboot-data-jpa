package com.example.spring.controller;

import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import com.example.spring.form.LoginForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping
	public String index(@ModelAttribute("form") LoginForm form) {
		return "login";
	}

	@GetMapping(params = "error")
	public String error(
			@ModelAttribute("form") LoginForm form,
			Model model, WebRequest request,
			@SessionAttribute(name = WebAttributes.AUTHENTICATION_EXCEPTION, required = false) Exception exception) {

		if (exception == null) {
			model.addAttribute("errors", "ログインに失敗しました。");
		} else {
			log.debug(exception.getMessage(), exception);
			model.addAttribute("errors", exception.getLocalizedMessage());
			request.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, RequestAttributes.SCOPE_SESSION);
		}

		return "login";
	}

}

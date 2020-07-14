package com.example.spring.controller;

import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.example.spring.form.*;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping()
	public String login(@ModelAttribute("form") UserForm form) {
		return "login";
	}

	@GetMapping(params = "error")
	public String login(
			Model model,
			@ModelAttribute("form") UserForm form,
			@SessionAttribute(name = WebAttributes.AUTHENTICATION_EXCEPTION, required = false) Exception exception) {

		if (exception == null) {
			model.addAttribute("message", "エラーが発生しました。");
		} else {
			if (exception instanceof DisabledException) {
				model.addAttribute("message", "アカウントが無効です。");
			} else if (exception instanceof LockedException) {
				model.addAttribute("message", "アカウントが凍結中です。");
			} else if (exception instanceof AccountExpiredException) {
				model.addAttribute("message", "アカウントの有効期限が切れています。");
			} else if (exception instanceof CredentialsExpiredException) {
				model.addAttribute("message", "パスワードの有効期限が切れています。");
			} else if (exception instanceof BadCredentialsException) {
				model.addAttribute("message", "アカウントまたはパスワードが一致しませんでした。");
			} else if (exception instanceof UsernameNotFoundException) {
				model.addAttribute("message", "アカウントまたはパスワードが一致しませんでした。");
			} else {
				model.addAttribute("message", "アカウントまたはパスワードが一致しませんでした。");
			}
		}
		return "login";
	}

	@GetMapping(params = "logout")
	public String logout(Model model) {
		model.addAttribute("message", "ログアウトが成功しました。");
		return "login";
	}
}

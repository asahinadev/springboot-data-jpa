package com.example.spring.controller;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.spring.db.entity.User;
import com.example.spring.db.service.MailSenderService;
import com.example.spring.db.service.UsersService;
import com.example.spring.form.UserForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/signin")
public class SigninController {

	@Autowired
	MailSenderService mailSenderService;

	@Autowired
	UsersService usersService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@GetMapping
	public String index(@ModelAttribute("form") UserForm form) {
		return "signin";
	}

	@PostMapping
	public String index(
			HttpServletRequest request,
			RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute("form") UserForm form, BindingResult result) {

		try {
			if (!result.hasErrors()) {

				User user = usersService.insert(form);
				redirectAttributes.addFlashAttribute("message", "登録に成功しました。");

				sendMail(request, user);

				if (user.isEnabled()) {

					SecurityContextHolder.getContext()
							.setAuthentication(new UsernamePasswordAuthenticationToken(
									user,
									user.getPassword(),
									user.getAuthorities()));

					return "redirect:/";
				} else {
					return "redirect:/signin/complete";
				}
			}
		} catch (DataIntegrityViolationException exception) {
			Throwable routeCause = exception.getCause().getCause();

			if (routeCause instanceof SQLIntegrityConstraintViolationException) {
				SQLException ex = (SQLException) routeCause;

				String msg = ex.getMessage();
				String code = ex.getSQLState() + "";

				if (msg.contains("idx_u_users_username")) {
					result.rejectValue("username", code, "利用できません");

				} else if (msg.contains("idx_u_users_email")) {
					result.rejectValue("email", code, "登録済みのメールアドレスです");

				}

			} else {
				throw exception;
			}
		} catch (RuntimeException exception) {
			log.warn("{} {}", exception.getClass().getName(), exception.getMessage(), exception);
			throw exception;
		}

		if (log.isDebugEnabled()) {
			result.getAllErrors().stream().forEach(error -> {
				log.debug("{} : {}", error.getObjectName(), error.getDefaultMessage());
			});
		}

		return "signin";
	}

	void sendMail(HttpServletRequest request, User user) {

		String subject = String.format("[%s] 登録完了通知", "アプリ名");
		String template = "signin";

		Map<String, Object> variables = new HashMap<>();
		variables.put("user", user);
		variables.put("login_url",
				UriComponentsBuilder.fromHttpRequest(new ServletServerHttpRequest(request))
						.replacePath("/login").toUriString());

		mailSenderService.sendHtml(user.getEmail(), subject, template, variables);
	}

}

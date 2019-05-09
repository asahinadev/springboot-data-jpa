package com.example.spring.controller.test;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.spring.db.entity.User;
import com.example.spring.db.service.MailSenderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/mail/test")
public class MailController {

	@Autowired
	MailSenderService mailSenderService;

	@GetMapping
	public String index(@AuthenticationPrincipal User user) {

		String subject = String.format("[%s] シンプルメール", "TEST");
		String body = "テストメールの送信です";

		mailSenderService.sendText(user.getEmail(), subject, body);

		return "OK";
	}

	@GetMapping("signin")
	public String signin(HttpServletRequest request, @AuthenticationPrincipal User user) {

		String subject = String.format("[%s] 登録完了通知", "TEST");
		String template = "signin";

		Map<String, Object> variables = new HashMap<>();
		variables.put("user", user);
		variables.put("login_url",
				UriComponentsBuilder.fromHttpRequest(new ServletServerHttpRequest(request))
						.replacePath("/login").toUriString());

		mailSenderService.sendHtml(user.getEmail(), subject, template, variables);

		return "OK";
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(MailAuthenticationException.class)
	public String errors(MailAuthenticationException exception) {
		log.error(exception.getMessage());
		return "認証情報が誤っています。";
	}

	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	@ExceptionHandler(Exception.class)
	public String errors(Exception exception) {
		log.error(exception.getMessage());
		return "再実行をお願いします。";
	}

}

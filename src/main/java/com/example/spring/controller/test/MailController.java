package com.example.spring.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.config.AppMailProperties;
import com.example.spring.db.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/mail/test")
public class MailController {

	@Autowired
	AppMailProperties appMailProperties;

	@Autowired
	MailSender mailSender;

	@GetMapping
	public String index(@AuthenticationPrincipal User user) {

		SimpleMailMessage msg = new SimpleMailMessage();

		if (StringUtils.isEmpty(appMailProperties.getFromName())) {
			msg.setFrom(appMailProperties.getFrom());
		} else {
			msg.setFrom(String.format("%s <%s>", appMailProperties.getFromName(), appMailProperties.getFrom()));
		}
		msg.setTo(user.getEmail());
		msg.setSubject("テストメール");//タイトルの設定
		msg.setText("テストメールの送信です"); //本文の設定

		log.debug("{}", appMailProperties);
		log.debug("{}", msg);

		this.mailSender.send(msg);

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

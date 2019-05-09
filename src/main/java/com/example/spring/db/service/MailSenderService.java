package com.example.spring.db.service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.example.spring.config.AppMailProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MailSenderService {

	@Autowired
	AppMailProperties appMailProperties;

	@Autowired
	JavaMailSender mailSender;

	public void sendHtml(String to, String subject, String template, Map<String, Object> variables) {

		log.debug("to        {}", to);
		log.debug("subject   {}", subject);
		log.debug("template  {}", template);
		log.debug("variables {}", variables);

		mailSender.send(new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper;
				helper = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.name());
				helper.setFrom(appMailProperties.getFrom());
				helper.setTo(to);
				helper.setSubject(subject);
				helper.setText(readBody(template, variables), true);
			}

		});
	}

	public void sendText(String to, String subject, String body) {

		log.debug("to        {}", to);
		log.debug("subject   {}", subject);
		log.debug("body      {}", body);

		mailSender.send(new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper;
				helper = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.name());
				helper.setFrom(appMailProperties.getFrom());
				helper.setTo(to);
				helper.setSubject(subject);
				helper.setText(body, false);
			}

		});
	}

	String readBody(String template, Map<String, Object> variables) {

		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(emailTemplateResolver());

		Context context = new Context();
		context.setVariables(variables);

		String result = templateEngine.process(template, context);

		return result;
	}

	ClassLoaderTemplateResolver emailTemplateResolver() {

		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

		templateResolver.setTemplateMode(appMailProperties.getTemplate().getTemplateMode());
		templateResolver.setPrefix(appMailProperties.getTemplate().getPrefix());
		templateResolver.setSuffix(appMailProperties.getTemplate().getSuffix());
		templateResolver.setCharacterEncoding(appMailProperties.getTemplate().getCharacterEncoding().name());
		templateResolver.setCacheable(appMailProperties.getTemplate().isCacheable());

		return templateResolver;

	}
}

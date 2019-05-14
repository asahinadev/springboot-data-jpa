package com.example.spring.config;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.thymeleaf.templatemode.TemplateMode;

import lombok.Data;

@Data
@Component
@SuppressWarnings("serial")
@ConfigurationProperties("app.mail")
public class AppMailProperties implements Serializable {
	@Data
	public static class TemplateProperties implements Serializable {
		String prefix = "templates/mail/";
		String suffix = ".html";
		Charset characterEncoding = StandardCharsets.UTF_8;
		TemplateMode templateMode = TemplateMode.HTML;
		boolean cacheable = false;
	}

	String from;
	String fromName;

	TemplateProperties template;
}

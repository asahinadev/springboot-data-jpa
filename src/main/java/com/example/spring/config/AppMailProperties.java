package com.example.spring.config;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Configuration
@SuppressWarnings("serial")
@ConfigurationProperties("app.mail")
public class AppMailProperties implements Serializable {
	String from;
	String fromName;
}

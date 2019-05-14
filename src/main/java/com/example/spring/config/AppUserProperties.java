package com.example.spring.config;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@SuppressWarnings("serial")
@ConfigurationProperties("app.user")
public class AppUserProperties implements Serializable {
	int credentialsExpireDays = 8;
}

package com.example.spring.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.spring.db.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AppController {

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	protected void login(User user) {
		log.debug("login user {}", user);
		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
	}

	protected String uri(String path, Map<String, Object> args) {
		HttpRequest request = new ServletServerHttpRequest(getRequest());
		return UriComponentsBuilder.fromHttpRequest(request).replacePath(path).buildAndExpand(args).toString();
	}

	protected String uri(String path, Object... args) {
		HttpRequest request = new ServletServerHttpRequest(getRequest());
		return UriComponentsBuilder.fromHttpRequest(request).replacePath(path).build(args).toString();
	}

	protected String path(String path, Map<String, Object> args) {
		return UriComponentsBuilder.fromPath(path).build(args).toString();
	}

	protected String path(String path, Object... args) {
		return UriComponentsBuilder.fromPath(path).build(args).toString();
	}

	protected ServletRequestAttributes getServletRequestAttributes() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
	}

	protected HttpServletRequest getRequest() {
		return getServletRequestAttributes().getRequest();
	}

	protected HttpServletResponse getResponse() {
		return getServletRequestAttributes().getResponse();
	}

	@SuppressWarnings("unchecked")
	protected <T> T getRequestAttribute(String name) {
		return (T) getServletRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_REQUEST);
	}

	@SuppressWarnings("unchecked")
	protected <T> T getSessionAttribute(String name) {
		return (T) getServletRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
	}

	protected void setRequestAttribute(String name, Object value) {
		getServletRequestAttributes().setAttribute(name, value, RequestAttributes.SCOPE_REQUEST);
	}

	protected void setSessionAttribute(String name, Object value) {
		getServletRequestAttributes().setAttribute(name, value, RequestAttributes.SCOPE_SESSION);
	}

	protected void removeRequestAttribute(String name) {
		if (!hasRequestAttribute(name)) {
			return;
		}
		getServletRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_REQUEST);
	}

	protected void removeSessionAttribute(String name) {
		if (!hasSessionAttribute(name)) {
			return;
		}
		getServletRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
	}

	protected boolean hasRequestAttribute(String name) {
		return null != getRequestAttribute(name);
	}

	protected boolean hasSessionAttribute(String name) {
		return null != getSessionAttribute(name);
	}

	protected void debug(BindingResult result) {
		if (!log.isDebugEnabled()) {
			return;
		}
		result.getAllErrors().forEach(item -> log.debug("{}", item));
	}

}

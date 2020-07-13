package com.example.spring.filter;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.boot.web.servlet.filter.*;
import org.springframework.http.*;
import org.springframework.web.filter.*;
import org.springframework.web.util.*;

import lombok.*;
import lombok.extern.slf4j.*;

@Slf4j
public class RequestFilter extends GenericFilterBean implements OrderedFilter {

	@Override
	public void doFilter(ServletRequest sRequest, ServletResponse sResponce, FilterChain chain)
			throws IOException, ServletException {

		ContentCachingRequestWrapper request;
		ContentCachingResponseWrapper response;

		if (sRequest instanceof ContentCachingRequestWrapper) {
			request = (ContentCachingRequestWrapper) sRequest;
		} else {
			request = new ContentCachingRequestWrapper((HttpServletRequest) sRequest);
		}
		if (sResponce instanceof ContentCachingResponseWrapper) {
			response = (ContentCachingResponseWrapper) sResponce;
		} else {
			response = new ContentCachingResponseWrapper((HttpServletResponse) sResponce);
		}

		before(request, response);
		chain.doFilter(request, response);
		after(request, response);
	}

	@Override
	public int getOrder() {

		return 0;
	}

	protected void before(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) {

		if (!logFilter(request)) {
			log.debug("[START] PATH={}, HEADER={}, REQUEST={}",
					getPath(request), getHeaders(request), getBody(request));
		}

	}

	protected void after(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response)
			throws IOException {

		if (!logFilter(request)) {
			log.debug("[END  ] PATH={}, HEADER={}, REQUEST={}",
					getPath(request), getHeaders(request), getBody(request));
		}

		response.copyBodyToResponse();
	}

	protected String getPath(ContentCachingRequestWrapper request) {

		return String.format("%-5s %s", request.getMethod(), request.getServletPath());
	}

	protected String getHeaders(ContentCachingRequestWrapper request) {

		HttpHeaders headers = new HttpHeaders();
		Enumeration<String> names = request.getHeaderNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getHeader(name);
			headers.add(name, value);
		}

		return headers.toString();
	}

	protected String getBody(ContentCachingRequestWrapper request) {

		return new String(request.getContentAsByteArray(), StandardCharsets.UTF_8);
	}

	protected String getBody(ContentCachingResponseWrapper response) {

		return new String(response.getContentAsByteArray(), StandardCharsets.UTF_8);
	}

	@SneakyThrows
	protected boolean logFilter(ContentCachingRequestWrapper request) {

		if (!log.isDebugEnabled()) {
			return true;
		}
		String path = request.getServletPath();

		if (path.startsWith("/webjars")) {
			return true;
		} else if (path.startsWith("/js")) {
			return true;
		} else if (path.startsWith("/css")) {
			return true;
		} else if (path.startsWith("/image")) {
			return true;
		} else if (path.startsWith("/font")) {
			return true;
		}

		return false;
	}
}

package com.poc.pricing.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.pricing.dto.ErrorDetail;

import lombok.extern.slf4j.Slf4j;

@Component
@Order(1)
@Slf4j
@WebFilter(urlPatterns = "/v1/*")
public class AuthorizationFilter implements Filter {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${auth.jwt.service.url}")
	private String authJwtServiceUrl;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("########## Initiating AuthenticationFilter filter ##########");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		final String authorization = request.getHeader("Authorization");
		if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
			String base64Credentials = authorization.substring("Basic".length()).trim();
			byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
			String credentials = new String(credDecoded, StandardCharsets.UTF_8);
			final String[] values = credentials.split(":", 2);
			if (values[0].equalsIgnoreCase("root") && values[1].equalsIgnoreCase("root")) {
				filterChain.doFilter(request, response);
			} else {
				setStatus(response);
			}
		} else {
			final String uri = authJwtServiceUrl;
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer " + authorization);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
			try {
				restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
				filterChain.doFilter(request, response);
			} catch (Exception e) {
				setStatus(response);
			}
		}

	}

	private void setStatus(HttpServletResponse response) throws IOException {
		ErrorDetail errorDetail = new ErrorDetail(new Date(), "Invalid Crendentials/Credential not found",
				String.valueOf(HttpServletResponse.SC_UNAUTHORIZED), "Authorization Failure");
		byte[] responseToSend = restResponseBytes(errorDetail);
		response.setHeader("Content-Type", "application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getOutputStream().write(responseToSend);
	}

	@Override
	public void destroy() {
	}

	private byte[] restResponseBytes(ErrorDetail eErrorResponse) throws IOException {
		String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
		return serialized.getBytes();
	}
}

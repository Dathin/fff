package me.pedrocaires.fff.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.pedrocaires.fff.exception.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilterExceptionHandler implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	public SecurityFilterExceptionHandler(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException ex) throws IOException, ServletException {
		httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
		httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new ExceptionResponse(ex.getMessage())));
		httpServletResponse.getWriter().flush();
		httpServletResponse.getWriter().close();
	}

}

package me.pedrocaires.fff.security;

import me.pedrocaires.fff.exception.CustomAuthenticationException;
import me.pedrocaires.fff.exception.UnauthorizedException;
import me.pedrocaires.fff.user.JwtService;
import me.pedrocaires.fff.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static me.pedrocaires.fff.security.OpenEndpoints.UN_AUTH_CUSTOM_ENDPOINTS;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	private final Logger securityFilterLogger = LoggerFactory.getLogger(SecurityFilter.class);

	private final JwtService jwtService;

	private final SecurityFilterExceptionHandler securityFilterExceptionHandler;

	private final UserService userService;

	public SecurityFilter(JwtService jwtService, SecurityFilterExceptionHandler securityFilterExceptionHandler,
			UserService userService) {
		this.jwtService = jwtService;
		this.securityFilterExceptionHandler = securityFilterExceptionHandler;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		var token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
		var path = httpServletRequest.getRequestURI();
		try {
			if (token != null && Arrays.stream(UN_AUTH_CUSTOM_ENDPOINTS).noneMatch(s -> s.equals(path))) {
				setUserFromToken(token);
			}
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}
		catch (UnauthorizedException ex) {
			securityFilterLogger.error("Security filter caught", ex);
			securityFilterExceptionHandler.commence(httpServletRequest, httpServletResponse,
					new CustomAuthenticationException(ex.getMessage()));
		}
	}

	private void setUserFromToken(String token) {
		var user = jwtService.validateToken(token.replace("Bearer ", ""));
		userService.setAuthenticatedUser(user);
	}

}

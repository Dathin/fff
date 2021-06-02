package me.pedrocaires.fff.security;

import me.pedrocaires.fff.exception.UnauthorizedException;
import me.pedrocaires.fff.user.JwtService;
import me.pedrocaires.fff.user.UserService;
import me.pedrocaires.fff.user.model.User;
import me.pedrocaires.fff.user.model.UserToken;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecurityFilterTest {

	@Mock
	JwtService jwtService;

	@Mock
	SecurityFilterExceptionHandler securityFilterExceptionHandler;

	@Mock
	UserService userService;

	@Mock
	HttpServletRequest httpServletRequest;

	@Mock
	HttpServletResponse httpServletResponse;

	@Mock
	FilterChain filterChain;

	@InjectMocks
	SecurityFilter securityFilter;

	@Test
	void shouldJustDoFilterOnNullToken() throws ServletException, IOException {
		when(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(null);

		securityFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);

		verify(filterChain).doFilter(httpServletRequest, httpServletResponse);
		verify(userService, never()).setAuthenticatedUser(any());
		verify(securityFilterExceptionHandler, never()).commence(any(), any(), any());
	}

	@Test
	void shouldSetUserFromToken() throws ServletException, IOException {
		var token = "notNullToken";
		var userToken = new UserToken();
		when(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(token);
		when(jwtService.validateToken(token)).thenReturn(userToken);

		securityFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);

		verify(filterChain).doFilter(httpServletRequest, httpServletResponse);
		verify(userService).setAuthenticatedUser(userToken);
		verify(securityFilterExceptionHandler, never()).commence(any(), any(), any());
	}

	@Test
	void shouldCommenceOnTokenError() throws ServletException, IOException {
		var token = "tokenWithErrors";
		when(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(token);
		when(jwtService.validateToken(token)).thenThrow(UnauthorizedException.class);

		securityFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);

		verify(filterChain, never()).doFilter(httpServletRequest, httpServletResponse);
		verify(userService, never()).setAuthenticatedUser(any());
		verify(securityFilterExceptionHandler).commence(any(), any(), any());
	}

}
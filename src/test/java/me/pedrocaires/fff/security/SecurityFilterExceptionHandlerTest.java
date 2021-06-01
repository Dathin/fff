package me.pedrocaires.fff.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecurityFilterExceptionHandlerTest {

    @Mock
    ObjectMapper objectMapper;

    @Mock
    HttpServletRequest httpServletRequest;

    @Mock
    HttpServletResponse httpServletResponse;

    @Mock
    AuthenticationException ex;

    @Mock
    PrintWriter printWriter;

    @InjectMocks
    SecurityFilterExceptionHandler securityFilterExceptionHandler;

    @BeforeEach
    void setUp() throws IOException {
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
    }

    @Test
    void shouldSetResponseHeaders() throws ServletException, IOException {
        securityFilterExceptionHandler.commence(httpServletRequest, httpServletResponse, ex);

        verify(httpServletResponse).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
    }

    @Test
    void shouldSetUnauthorizedStatus() throws ServletException, IOException {
        securityFilterExceptionHandler.commence(httpServletRequest, httpServletResponse, ex);

        verify(httpServletResponse).setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    void shouldWriteResponseBody() throws ServletException, IOException {
        var bodyString = "MyResponseBody";
        when(objectMapper.writeValueAsString(any())).thenReturn(bodyString);

        securityFilterExceptionHandler.commence(httpServletRequest, httpServletResponse, ex);

        verify(ex).getMessage();
        verify(printWriter).write(bodyString);
        verify(printWriter).close();
        verify(printWriter).flush();
    }
}
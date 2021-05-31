package me.pedrocaires.fff.config.security;

import me.pedrocaires.fff.exception.CustomAuthenticationException;
import me.pedrocaires.fff.exception.UnauthorizedException;
import me.pedrocaires.fff.service.JwtService;
import me.pedrocaires.fff.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final SecurityFilterExceptionHandler securityFilterExceptionHandler;

    private final UserService userService;


    public SecurityFilter(JwtService jwtService, SecurityFilterExceptionHandler securityFilterExceptionHandler, UserService userService) {
        this.jwtService = jwtService;
        this.securityFilterExceptionHandler = securityFilterExceptionHandler;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        var token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        try {
            if(token != null){
                var user = jwtService.validateToken(token.replace("Bearer ", ""));
                userService.setSecurityContextHolder(user);
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (UnauthorizedException ex){
            ex.printStackTrace();
            securityFilterExceptionHandler.commence(httpServletRequest, httpServletResponse, new CustomAuthenticationException(ex.getMessage()));
        }
    }
}

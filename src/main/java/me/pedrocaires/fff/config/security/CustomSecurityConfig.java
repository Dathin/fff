package me.pedrocaires.fff.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityFilter securityFilter;
    private final SecurityFilterExceptionHandler securityFilterExceptionHandler;

    public CustomSecurityConfig(SecurityFilter securityFilter, SecurityFilterExceptionHandler securityFilterExceptionHandler) {
        this.securityFilter = securityFilter;
        this.securityFilterExceptionHandler = securityFilterExceptionHandler;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        var unAuthEndpoints = new String[]{"/swagger-ui/**", "/v3/**", "/swagger-resources/**", "/user/login"};
        http.cors()
            .and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authorizeRequests()
            .antMatchers(unAuthEndpoints).permitAll()
            .anyRequest().authenticated()
            .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling().authenticationEntryPoint(securityFilterExceptionHandler);
    }
}

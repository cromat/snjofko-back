package com.example.snjofko.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthEntryPont jwtAuthEntryPont;
    private final JwtRequestFilter jwtRequestFilter;
    private final AuthenticationManager authenticationManager;

    public SecurityConfig(JwtAuthEntryPont jwtAuthEntryPont,
                          JwtRequestFilter jwtRequestFilter,
                          AuthenticationManager authenticationManager) {
        this.jwtAuthEntryPont = jwtAuthEntryPont;
        this.jwtRequestFilter = jwtRequestFilter;
        this.authenticationManager = authenticationManager;
    }

    public static final String AUTH_PATH = "/api/users/";
    public static final String LOGIN_PATH = AUTH_PATH + "login";

    private final String[] IGNORING_ROUTES = {
            LOGIN_PATH,
            AUTH_PATH + "signup",
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();

        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        http.exceptionHandling()
                .authenticationEntryPoint(jwtAuthEntryPont)
                .and();

        http.authorizeRequests()
                .antMatchers(IGNORING_ROUTES).permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}

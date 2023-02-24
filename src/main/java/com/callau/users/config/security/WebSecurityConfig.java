package com.callau.users.config.security;

import com.callau.users.repository.UserRepository;
import com.callau.users.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/users", "/authentication").permitAll()
                .antMatchers(HttpMethod.GET, "/users/verify").permitAll()
                .antMatchers(HttpMethod.POST, "/users/password/reset").permitAll()
                .antMatchers(HttpMethod.POST, "/users/password/new").permitAll()
                .antMatchers("/v3/**", "/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new AuthenticationTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class)
                .logout().permitAll()
                .and()
                .csrf().disable();

        return http.build();
    }

}

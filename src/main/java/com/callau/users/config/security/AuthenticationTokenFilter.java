package com.callau.users.config.security;

import com.callau.users.model.User;
import com.callau.users.repository.UserRepository;
import com.callau.users.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository repo;

    public AuthenticationTokenFilter(TokenService tokenService, UserRepository repo) {
        this.tokenService = tokenService;
        this.repo = repo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        if (tokenService.isTokenValid(token)) {
            authenticateClient(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateClient(String token) {
        Long userId = tokenService.getUserId(token);
        User user = repo.findById(userId).orElseThrow();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        } else {
            return token.substring(7);
        }
    }

}

package com.callau.users.controller;

import com.callau.users.dto.AuthenticationRequest;
import com.callau.users.dto.TokenDTO;
import com.callau.users.exception.UnverifiedEmailException;
import com.callau.users.model.User;
import com.callau.users.model.UserStatus;
import com.callau.users.repository.UserRepository;
import com.callau.users.service.TokenService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<TokenDTO> authenticate(@RequestBody @Valid AuthenticationRequest dto) {
        UsernamePasswordAuthenticationToken data = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow();
        if (user.getStatus().equals(UserStatus.UNVERIFIED)) {
            throw new UnverifiedEmailException();
        }
        Authentication authentication = authenticationManager.authenticate(data);
        String token = tokenService.generateToken(authentication);
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-access-token", token);
        return new ResponseEntity<>(new TokenDTO(token, "Bearer"), headers, HttpStatus.OK);
    }

}

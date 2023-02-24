package com.callau.users.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@RequiredArgsConstructor
public class AuthenticationRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

}

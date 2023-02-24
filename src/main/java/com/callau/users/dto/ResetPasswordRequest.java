package com.callau.users.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ResetPasswordRequest {

    @NotEmpty
    public String email;

}

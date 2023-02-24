package com.callau.users.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PasswordChangeRequest {

    @NotBlank
    private String oldPassword;

    @NotBlank
    @Size(min=8)
    private String newPassword;

}

package com.callau.users.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class NewPasswordRequest {

    @NotEmpty
    @Size(min = 8)
    private String newPassword;

}

package com.callau.users.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateUserRequest {

    @NotBlank
    private String name;

}

package com.callau.users.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class NewUserPreferencesRequest {

    @NotEmpty
    private String currency;

    @NotEmpty
    private String language;

    @NotEmpty
    private String timeZone;

}

package com.callau.users.dto;

import lombok.Data;

@Data
public class TokenDTO {

    private final String type;
    private final String token;

    public TokenDTO(String token, String type) {
        this.token = token;
        this.type = type;
    }

}

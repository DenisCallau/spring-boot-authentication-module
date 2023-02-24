package com.callau.users.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorDTO {

    private Integer status;
    private String message;
    private String field;

    public ErrorDTO(Integer status, String message, String field) {
        this.status = status;
        this.message = message;
        this.field = field;
    }

    public ErrorDTO(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

}

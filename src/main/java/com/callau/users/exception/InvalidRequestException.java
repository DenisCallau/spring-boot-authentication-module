package com.callau.users.exception;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException() {
        super("Invalid request");
    }

}

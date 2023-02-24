package com.callau.users.exception;

public class AlreadyRegisteredEmailException extends RuntimeException {

    public AlreadyRegisteredEmailException() {
        super("There's already a user registered with this email");
    }

}

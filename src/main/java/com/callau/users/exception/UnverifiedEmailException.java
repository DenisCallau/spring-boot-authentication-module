package com.callau.users.exception;

public class UnverifiedEmailException extends RuntimeException {

    public UnverifiedEmailException() {
        super("You must verify your email address first");
    }

}

package com.callau.users.exception;

public class OldPasswordIncorrectException extends RuntimeException {

    public OldPasswordIncorrectException() {
        super("The old password is incorrect");
    }

}

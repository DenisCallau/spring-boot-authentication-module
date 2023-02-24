package com.callau.users.exception;

public class UserAlreadyVerifiedException extends RuntimeException {

    public UserAlreadyVerifiedException() {
        super("The user is already verified");
    }

}

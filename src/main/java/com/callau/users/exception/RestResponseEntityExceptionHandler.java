package com.callau.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyRegisteredEmailException.class)
    public ErrorDTO handle(AlreadyRegisteredEmailException exception) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OldPasswordIncorrectException.class)
    public ErrorDTO handle(OldPasswordIncorrectException exception) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnverifiedEmailException.class)
    public ErrorDTO handle(UnverifiedEmailException exception) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthenticationException.class)
    public ErrorDTO handle(AuthenticationException exception) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "The email or the password is incorrect");
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ErrorDTO handle(UsernameNotFoundException exception) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler(UserAlreadyVerifiedException.class)
    public ErrorDTO handle(UserAlreadyVerifiedException exception) {
        return new ErrorDTO(HttpStatus.FORBIDDEN.value(), exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRequestException.class)
    public ErrorDTO handle(InvalidRequestException exception) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDTO handle(UserNotFoundException exception) {
        return new ErrorDTO(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

}

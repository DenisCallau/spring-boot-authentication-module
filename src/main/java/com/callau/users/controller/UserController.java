package com.callau.users.controller;

import com.callau.users.dto.*;
import com.callau.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<NewUserResponse> add(@Valid @RequestBody NewUserRequest dto) {
        NewUserResponse response = service.add(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/verify")
    public ResponseEntity<Object> verifyUser(@RequestParam("verifyCode") String verifyCode) {
        service.verifyUser(verifyCode);
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity<UpdateUserResponse> update(@Valid @RequestBody UpdateUserRequest dto) {
        UpdateUserResponse response = service.update(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/password/change")
    public ResponseEntity<Object> changePassword(@Valid @RequestBody PasswordChangeRequest dto) {
        service.changePassword(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/password/reset")
    public ResponseEntity<Object> resetPassword(@RequestBody @Valid ResetPasswordRequest dto) {
        service.resetPassword(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/password/new")
    public ResponseEntity<Object> setNewPassword(@RequestParam("verifyCode") String verifyCode, @RequestBody @Valid NewPasswordRequest dto) {
        service.setNewPassword(verifyCode, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public void terminateAccount() {
        service.terminateAccount();
    }

    @GetMapping
    public String hello() {
        return "Hello";
    }

}

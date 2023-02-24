package com.callau.users.controller;

import com.callau.users.dto.UserPreferencesDTO;
import com.callau.users.service.UserPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user-preferences")
public class UserPreferencesController {

    @Autowired
    private UserPreferencesService service;

    @GetMapping
    public ResponseEntity<UserPreferencesDTO> getCurrentUserPreferences() {
        UserPreferencesDTO dto = service.getCurrentUserPreferences();
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<UserPreferencesDTO> update(@Valid @RequestBody UserPreferencesDTO dto) {
        UserPreferencesDTO response = service.update(dto);
        return ResponseEntity.ok(response);
    }

}

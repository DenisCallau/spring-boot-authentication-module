package com.callau.users.dto;

import com.callau.users.model.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NewUserResponse {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min=8)
    private String password;

    @NotNull
    private UserPreferencesDTO userPreferences;

    public NewUserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.userPreferences = new UserPreferencesDTO(user.getUserPreferences());
    }

}

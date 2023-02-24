package com.callau.users.dto;

import com.callau.users.model.User;
import lombok.Data;

@Data
public class UpdateUserResponse {

    private Long id;

    private String name;

    private String email;

    private UserPreferencesDTO userPreferences;

    public UpdateUserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.userPreferences = new UserPreferencesDTO(user.getUserPreferences());
    }

}

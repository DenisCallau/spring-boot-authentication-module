package com.callau.users.dto;

import com.callau.users.model.UserPreferences;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class UserPreferencesDTO {

    @NotEmpty
    private String currency;

    @NotEmpty
    private String language;

    @NotEmpty
    private String timeZone;

    public UserPreferencesDTO(UserPreferences userPreferences) {
        this.currency = userPreferences.getCurrency().getCurrencyCode();
        this.language = userPreferences.getLanguage();
        this.timeZone = userPreferences.getTimeZone().getDisplayName();
    }

}

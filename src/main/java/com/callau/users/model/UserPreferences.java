package com.callau.users.model;

import com.callau.users.dto.UserPreferencesDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Currency;
import java.util.TimeZone;

@Entity
@Table(name = "user_preferences")
@Getter
@Setter
@NoArgsConstructor
public class UserPreferences extends AuditObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Currency currency;

    @NotBlank
    private String language;

    @NotNull
    private TimeZone timeZone;

    public UserPreferences(UserPreferencesDTO dto) {
        this.currency = Currency.getInstance(dto.getCurrency());
        this.language = dto.getLanguage();
        this.timeZone = TimeZone.getTimeZone(dto.getTimeZone());
    }

}

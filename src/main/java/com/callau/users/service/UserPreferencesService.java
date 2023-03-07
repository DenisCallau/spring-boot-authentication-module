package com.callau.users.service;

import com.callau.users.dto.UserPreferencesDTO;
import com.callau.users.model.User;
import com.callau.users.model.UserPreferences;
import com.callau.users.repository.UserPreferencesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Currency;
import java.util.TimeZone;

@Service
public class UserPreferencesService extends BaseService {

    private final UserPreferencesRepository userPreferencesRepository;

    public UserPreferencesService(UserPreferencesRepository userPreferencesRepository) {
        this.userPreferencesRepository = userPreferencesRepository;
    }

    public UserPreferencesDTO getCurrentUserPreferences() {
        User user = getCurrentUser();
        return new UserPreferencesDTO(user.getUserPreferences());
    }

    @Transactional
    public UserPreferencesDTO update(UserPreferencesDTO dto) {
        User user = getCurrentUser();
        UserPreferences userPreferences = user.getUserPreferences();
        userPreferences.setCurrency(Currency.getInstance(dto.getCurrency()));
        userPreferences.setLanguage(dto.getLanguage());
        userPreferences.setTimeZone(TimeZone.getTimeZone(dto.getTimeZone()));
        userPreferencesRepository.save(userPreferences);
        return new UserPreferencesDTO(userPreferences);
    }

}

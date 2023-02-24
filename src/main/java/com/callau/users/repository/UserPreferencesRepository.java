package com.callau.users.repository;

import com.callau.users.model.UserPreferences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Long> {
}
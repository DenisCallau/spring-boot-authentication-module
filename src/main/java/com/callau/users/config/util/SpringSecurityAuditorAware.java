package com.callau.users.config.util;

import com.callau.users.model.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return Optional.of(user.getId());
        } catch (Exception e) {
            return Optional.of(0L);
        }
    }

}

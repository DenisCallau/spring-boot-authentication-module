package com.callau.users.repository;

import com.callau.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);

    Optional<User> findByVerificationCode(String verifyCode);

}

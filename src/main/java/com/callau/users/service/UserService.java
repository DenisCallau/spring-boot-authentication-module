package com.callau.users.service;

import com.callau.users.dto.*;
import com.callau.users.exception.*;
import com.callau.users.http.EmailClient;
import com.callau.users.http.ResetPasswordEmailRequest;
import com.callau.users.http.VerificationEmailRequest;
import com.callau.users.model.User;
import com.callau.users.repository.UserPreferencesRepository;
import com.callau.users.model.UserStatus;
import com.callau.users.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService extends BaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPreferencesRepository userPreferencesRepository;

    @Autowired
    private EmailClient emailClient;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public NewUserResponse add(NewUserRequest request) {
        Optional<User> optional = userRepository.findByEmail(request.getEmail());
        if (optional.isPresent()) {
            throw new AlreadyRegisteredEmailException();
        }
        String verificationCode = RandomString.make(64);
        User user = new User(request);
        user.setVerificationCode(verificationCode);
        userPreferencesRepository.save(user.getUserPreferences());
        userRepository.save(user);

        VerificationEmailRequest verificationEmailRequest = new VerificationEmailRequest(user.getVerificationCode());
        verificationEmailRequest.setOwnerRef(user.getId().toString());
        verificationEmailRequest.setRecipient(user.getEmail());
        emailClient.sendVerificationEmail(verificationEmailRequest);
        return new NewUserResponse(user);
    }

    @Transactional
    public void verifyUser(String verifyCode) {
        User user = userRepository.findByVerificationCode(verifyCode).orElseThrow();
        if (!user.getStatus().equals(UserStatus.UNVERIFIED)) {
            throw new UserAlreadyVerifiedException();
        }
        if (user.getVerificationCode().equals(verifyCode)) {
            user.setStatus(UserStatus.ACTIVE);
            user.setVerificationCode(null);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Verification failed");
        }
    }

    @Transactional
    public UpdateUserResponse update(UpdateUserRequest request) {
        User user = getCurrentUser();
        user.setName(request.getName());
        userRepository.save(user);
        return new UpdateUserResponse(user);
    }

    @Transactional
    public void resetPassword(ResetPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(UserNotFoundException::new);
        String verificationCode = RandomString.make(64);
        user.setVerificationCode(verificationCode);
        user.setStatus(UserStatus.LOCKED);
        userRepository.save(user);
        ResetPasswordEmailRequest resetPasswordEmailRequest = new ResetPasswordEmailRequest(user.getVerificationCode());
        resetPasswordEmailRequest.setOwnerRef(user.getId().toString());
        resetPasswordEmailRequest.setRecipient(user.getEmail());
        emailClient.sendResetPasswordEmail(resetPasswordEmailRequest);
    }

    @Transactional
    public void setNewPassword(String verifyCode, NewPasswordRequest request) {
        User user = userRepository.findByVerificationCode(verifyCode).orElseThrow(InvalidRequestException::new);
        if (user.getVerificationCode().equals(verifyCode)) {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            user.setStatus(UserStatus.ACTIVE);
            user.setVerificationCode(null);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Verification failed");
        }
    }

    @Transactional
    public void changePassword(PasswordChangeRequest request) {
        User user = getCurrentUser();
        if (new BCryptPasswordEncoder().matches(request.getOldPassword(), getCurrentUser().getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new OldPasswordIncorrectException();
        }
    }

    @Transactional
    public void updateLoginData(User user) {
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
    }

    @Transactional
    public void terminateAccount() {
        userRepository.delete(getCurrentUser());
    }

}

package com.callau.users.model;

import com.callau.users.dto.NewUserRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends AuditObject implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "user_preferences_id")
    private UserPreferences userPreferences;

    private String verificationCode;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private LocalDateTime lastLogin;

    public User(NewUserRequest dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.password = new BCryptPasswordEncoder().encode(dto.getPassword());
        this.userPreferences = new UserPreferences(dto.getUserPreferences());
        this.status = UserStatus.UNVERIFIED;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

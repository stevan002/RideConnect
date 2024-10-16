package com.rideconnect.server.model;

import com.rideconnect.server.model.enumeration.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "Bio cannot be empty")
    private String bio;

    @Column(name = "phone_number", nullable = false, unique = true)
    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "\\+?[0-9. ()-]{7,25}", message = "Phone number should be valid")
    private String phoneNumber;

    @Column(unique = true)
    private String photo;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role cannot be null")
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

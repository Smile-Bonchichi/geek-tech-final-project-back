package kg.geektech.dostavkakgbackend.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.geektech.dostavkakgbackend.entity.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity implements UserDetails {
    @NotNull
    @Size(min = 14, max = 14, message = "Введите корректный ПИН")
    @Column(name = "pin", unique = true)
    String pin;

    @NotNull
    @Size(min = 2, message = "Введите корректный ФИО")
    @Column(name = "full_name")
    String fullName;

    @NotNull
    @Size(min = 2, message = "Введите корректный номер телефона")
    @Column(name = "phone_number")
    String phoneNumber;

    @NotNull
    @Email(message = "Введите корректный email")
    @Column(name = "email")
    String email;

    @NotNull
    @Size(min = 2, message = "Введите корректный пароль от 3 символов")
    @Column(name = "password")
    String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    Role role;

    @NotNull
    @Column(name = "is_enabled")
    boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
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
        return enabled;
    }

    @Getter
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public enum Role {
        USER("user"),
        ADMIN("admin"),
        ;

        final String name;
    }
}

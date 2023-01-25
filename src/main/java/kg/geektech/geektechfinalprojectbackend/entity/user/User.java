package kg.geektech.geektechfinalprojectbackend.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kg.geektech.geektechfinalprojectbackend.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {
    @NotNull
    @Length(min = 14, max = 14, message = "Введите корректный ПИН")
    @Column(name = "pin")
    private String pin;

    @NotNull
    @Length(min = 2, message = "Введите ФИО")
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "ad_group_role",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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

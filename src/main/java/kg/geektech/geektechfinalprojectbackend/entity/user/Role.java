package kg.geektech.geektechfinalprojectbackend.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import kg.geektech.geektechfinalprojectbackend.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity implements GrantedAuthority {
    @NotEmpty(message = "Введите название")
    @Column(name = "name")
    String name;

    @NotEmpty(message = "Введите описание")
    @Column(name = "description")
    String description;

    @Override
    public String getAuthority() {
        return name;
    }
}

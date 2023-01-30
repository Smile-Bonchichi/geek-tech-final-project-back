package kg.geektech.geektechfinalprojectbackend.entity.product;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import kg.geektech.geektechfinalprojectbackend.entity.BaseEntity;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Builder
@Table(name = "favorite_products")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FavoriteProduct extends BaseEntity {
    @NotNull
    @OneToOne
    @JoinColumn(name = "product_id")
    Product product;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
}

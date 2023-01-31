package kg.geektech.geektechfinalprojectbackend.entity.basket;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import kg.geektech.geektechfinalprojectbackend.entity.BaseEntity;
import kg.geektech.geektechfinalprojectbackend.entity.product.Product;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Builder
@Table(name = "baskets")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Basket extends BaseEntity {
    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @NotNull
    @OneToOne
    @JoinColumn(name = "product_id")
    Product product;
}

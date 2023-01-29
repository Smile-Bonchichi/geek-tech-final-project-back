package kg.geektech.geektechfinalprojectbackend.entity.basket;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kg.geektech.geektechfinalprojectbackend.entity.BaseEntity;
import kg.geektech.geektechfinalprojectbackend.entity.product.Product;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "basket_product",
            joinColumns = @JoinColumn(name = "basket_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    List<Product> products;
}

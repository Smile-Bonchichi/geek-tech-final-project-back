package kg.geektech.dostavkakgbackend.entity.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kg.geektech.dostavkakgbackend.entity.BaseEntity;
import kg.geektech.dostavkakgbackend.entity.product.Product;
import kg.geektech.dostavkakgbackend.entity.user.User;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "orders")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends BaseEntity {
    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
    @NotNull
    @Column(name = "is_accept")
    Boolean isAccept;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    List<Product> products;
}

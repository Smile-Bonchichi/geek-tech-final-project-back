package kg.geektech.dostavkakgbackend.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kg.geektech.dostavkakgbackend.entity.BaseEntity;
import kg.geektech.dostavkakgbackend.entity.category.Category;
import kg.geektech.dostavkakgbackend.entity.image.Image;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseEntity {
    @NotNull
    @Column(name = "name", unique = true)
    String name;
    @NotNull
    @Column(name = "description")
    String description;
    @NotNull
    @Column(name = "price")
    BigDecimal price;
    @NotNull
    @Column(name = "is_present")
    Boolean isPresent;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    List<Category> categories;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_image",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    List<Image> images;
}

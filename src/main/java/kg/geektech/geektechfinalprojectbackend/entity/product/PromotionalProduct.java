package kg.geektech.geektechfinalprojectbackend.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kg.geektech.geektechfinalprojectbackend.entity.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@Table(name = "promotional_products")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromotionalProduct extends BaseEntity {
    @NotNull
    @OneToOne
    @JoinColumn(name = "product_id")
    Product product;

    @NotNull
    @Column(name = "promotion")
    BigDecimal promotion;
}

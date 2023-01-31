package kg.geektech.geektechfinalprojectbackend.dto.product.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Модель данных избранного продукта")
public class FavoriteProductDto {
    @Schema(description = "Название продукта", example = "Суп")
    String name;
}

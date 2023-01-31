package kg.geektech.dostavkakgbackend.dto.product.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kg.geektech.dostavkakgbackend.dto.category.response.CategoryDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Модель данных CRUD продукта")
public class ProductDto {
    @Schema(description = "Название продукта", example = "Суп")
    String name;
    @Schema(description = "Описание продукта", example = "Вкусный, сытный, супер суп")
    String description;
    @Schema(description = "Цена продукта", example = "150")
    BigDecimal price;
    List<CategoryDto> categoryDtos;
}

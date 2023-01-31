package kg.geektech.dostavkakgbackend.dto.product.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kg.geektech.dostavkakgbackend.dto.category.CategoryDto;
import kg.geektech.dostavkakgbackend.dto.image.ImageDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Модель данных продукта")
public class ProductInfoDto {
    @Schema(description = "Название продукта", example = "Суп")
    String name;
    @Schema(description = "Описание продукта", example = "Вкусный, сытный, супер суп")
    String description;
    @Schema(description = "Цена продукта", example = "150")
    BigDecimal price;
    List<CategoryDto> categoryDtos;
    List<ImageDto> imageDtos;
}

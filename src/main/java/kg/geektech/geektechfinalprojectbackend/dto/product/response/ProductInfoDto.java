package kg.geektech.geektechfinalprojectbackend.dto.product.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kg.geektech.geektechfinalprojectbackend.dto.BaseResponse;
import kg.geektech.geektechfinalprojectbackend.dto.category.CategoryDto;
import kg.geektech.geektechfinalprojectbackend.dto.image.response.ImageDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Модель данных продукта")
public class ProductInfoDto extends BaseResponse {
    @Schema(description = "Название продукта", example = "Суп")
    String name;
    @Schema(description = "Описание продукта", example = "Вкусный, сытный, супер суп")
    String description;
    @Schema(description = "Цена продукта", example = "150")
    BigDecimal price;
    List<CategoryDto> categoryDtos;
    List<ImageDto> imageDtos;
}

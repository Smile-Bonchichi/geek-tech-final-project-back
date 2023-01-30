package kg.geektech.geektechfinalprojectbackend.dto.product.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kg.geektech.geektechfinalprojectbackend.dto.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Модель данных CRUD продукта")
public class ProductDto extends BaseResponse {
    @Schema(description = "Название продукта", example = "Суп")
    String name;
}

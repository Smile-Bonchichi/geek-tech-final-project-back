package kg.geektech.geektechfinalprojectbackend.dto.product.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import kg.geektech.geektechfinalprojectbackend.dto.BaseRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Модель данных изменяемого продукта")
public class ChangeProductDto extends BaseRequest {
    @Min(1)
    @Schema(description = "ID продукта", example = "1")
    Long id;
    @Size(min = 2, message = "Введите корректное название продукта")
    @Schema(description = "Название продукта", example = "Суп")
    String name;
    @Size(min = 2, message = "Введите корректное описание продукта")
    @Schema(description = "Описание продукта", example = "Вкусный, сытный, супер суп")
    String description;
    @Min(100)
    @Schema(description = "Цена продукта", example = "150")
    BigDecimal price;
    @Schema(description = "ID категорий товара", example = "[1, 2, 3]")
    List<Long> categoryId;
    @Schema(description = "Изображения продуктов")
    List<MultipartFile> images;
}

package kg.geektech.dostavkakgbackend.dto.product.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.geektech.dostavkakgbackend.dto.BaseRequest;
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
@Schema(name = "Модель данных добавляемого продукта")
public class AddProductDto extends BaseRequest {
    @NotNull
    @Size(min = 2, message = "Введите корректное название продукта")
    @Schema(description = "Название продукта", example = "Суп")
    String name;
    @NotNull
    @Size(min = 2, message = "Введите корректное описание продукта")
    @Schema(description = "Описание продукта", example = "Вкусный, сытный, супер суп")
    String description;
    @NotNull
    @Min(100)
    @Schema(description = "Цена продукта", example = "150")
    BigDecimal price;
    @NotNull
    @Schema(description = "ID категорий товара", example = "[1, 2, 3]")
    List<Long> categoryId;
}

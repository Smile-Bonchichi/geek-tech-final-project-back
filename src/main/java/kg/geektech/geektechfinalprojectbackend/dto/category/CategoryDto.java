package kg.geektech.geektechfinalprojectbackend.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.geektech.geektechfinalprojectbackend.dto.BaseRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Модель данных категории")
public class CategoryDto extends BaseRequest {
    @Min(1)
    @Schema(description = "ID категории", example = "1")
    Long id;
    @NotNull
    @Size(min = 2, message = "Название должно быть более 2 символов")
    @Schema(description = "Название", example = "Суп")
    String name;
}

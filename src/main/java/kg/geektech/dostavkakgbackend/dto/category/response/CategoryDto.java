package kg.geektech.dostavkakgbackend.dto.category.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kg.geektech.dostavkakgbackend.dto.image.ImageDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Модель данных категории")
public class CategoryDto {
    @Schema(description = "ID категории", example = "1")
    Long id;
    @Schema(description = "Название", example = "Жидкое")
    String name;
    List<ImageDto> imageDtos;
}

package kg.geektech.dostavkakgbackend.dto.image;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Модель данных изображений")
public class ImageDto {
    @Schema(description = "Ссылка на изображение", example = "https://res.cloudinary.com/...")
    String url;
    @Schema(description = "Дата добавления изображения", example = "2023-01-30 05:08:57.748343 +06:00")
    LocalDateTime createdAt;
}

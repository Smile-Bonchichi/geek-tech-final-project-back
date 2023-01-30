package kg.geektech.geektechfinalprojectbackend.dto.image.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kg.geektech.geektechfinalprojectbackend.dto.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Модель данных изображений")
public class ImageDto extends BaseResponse {
    @Schema(description = "Ссылка на изображение", example = "https://res.cloudinary.com/...")
    String url;
    @Schema(description = "Дата добавления изображения", example = "2023-01-30 05:08:57.748343 +06:00")
    LocalDateTime createdAt;
}

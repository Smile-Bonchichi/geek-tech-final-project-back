package kg.geektech.geektechfinalprojectbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseResponse {
    @Schema(description = "Сообщение", example = "Описание ошибки на сервере")
    String message;
}

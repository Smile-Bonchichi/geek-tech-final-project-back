package kg.geektech.dostavkakgbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kg.geektech.dostavkakgbackend.enums.ResultCode;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Модель данных ответа")
public class BaseResponse {
    @Schema(description = "Объект")
    Object result;
    @Schema(description = "Код запроса", example = "FAIL")
    ResultCode resultCode;
    @Schema(description = "Описание проблемы", example = "Bad request")
    String details;
}

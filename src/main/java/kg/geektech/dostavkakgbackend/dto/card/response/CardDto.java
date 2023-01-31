package kg.geektech.dostavkakgbackend.dto.card.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Модель данных авторизации")
public class CardDto {
    @Schema(description = "ФИО", example = "Уланов Нурдин")
    String fullName;
    @Schema(description = "Почта", example = "test@gmail.com")
    String email;
    @Schema(description = "Номер карты", example = "4444444444444444")
    String cardNumber;
}

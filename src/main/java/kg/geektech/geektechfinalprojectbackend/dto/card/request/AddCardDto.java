package kg.geektech.geektechfinalprojectbackend.dto.card.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.geektech.geektechfinalprojectbackend.dto.BaseRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Модель данных авторизации")
public class AddCardDto extends BaseRequest {
    @NotNull
    @Size(min = 16, max = 16, message = "Введите корректный номер карты")
    @Schema(description = "Номер карты", example = "4444444444444444")
    String cardNumber;
}

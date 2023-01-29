package kg.geektech.geektechfinalprojectbackend.dto.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
@Schema(name = "Модель данных регистрации")
public class RegistrationRequestDto extends BaseRequest {
    @NotNull
    @NotEmpty(message = "ПИН не должен быть пустым")
    @Size(min = 14, max = 14, message = "ПИН должен быть 14 символов")
    @Schema(description = "ПИН", example = "22212200001155")
    String pin;
    @NotNull
    @NotEmpty(message = "Email не должен быть пустым")
    @Email(message = "Введите корректный email")
    @Schema(description = "Почта", example = "test@gmail.com")
    String email;
    @NotNull
    @NotEmpty(message = "ФИО не должен быть пустым")
    @Size(min = 2, message = "Введите корректный ФИО")
    @Schema(description = "ФИО", example = "Уланов Нурдин")
    String fullName;
    @NotNull
    @Size(min = 2, message = "Введите корректный пароль от 3 символов")
    @Schema(description = "Пароль", example = "qwe!23")
    String password;
}

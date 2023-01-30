package kg.geektech.geektechfinalprojectbackend.dto.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
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
public class RegistrationDto extends BaseRequest {
    @NotNull
    @Size(min = 14, max = 14, message = "ПИН должен быть 14 символов")
    @Schema(description = "ПИН", example = "22212200001155")
    String pin;
    @NotNull
    @NotEmpty(message = "Email не должен быть пустым")
    @Email(message = "Введите корректный email")
    @Schema(description = "Почта", example = "test@gmail.com")
    String email;
    @NotNull
    @Size(min = 2, message = "Введите корректный ФИО")
    @Schema(description = "ФИО", example = "Уланов Нурдин")
    String fullName;
    @NotNull
    @Pattern(regexp = "")
    @Size(min = 13, max = 13, message = "Введите корректный номер телефона")
    @Schema(description = "Номер телефона", example = "+996779977288")
    String phoneNumber;
    @NotNull
    @Size(min = 2, message = "Введите корректный пароль от 3 символов")
    @Schema(description = "Пароль", example = "qwe!23")
    String password;
}

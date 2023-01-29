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
@Schema(name = "Модель данных авторизации")
public class AuthenticationRequestDto extends BaseRequest {
    @NotNull
    @NotEmpty(message = "Email не должен быть пустым")
    @Email(message = "Введите корректный email")
    @Schema(description = "Почта", example = "test@gmail.com")
    String email;
    @NotNull
    @Size(min = 2, message = "Введите корректный пароль от 3 символов")
    @Schema(description = "Пароль", example = "qwe!23")
    String password;
}

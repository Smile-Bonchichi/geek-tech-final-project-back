package kg.geektech.geektechfinalprojectbackend.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kg.geektech.geektechfinalprojectbackend.dto.BaseRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Модель данных обновления пользователя")
public class UpdateUserDto extends BaseRequest {
    @Pattern(regexp = "^[12][0-9]{13}$")
    @Size(min = 14, max = 14, message = "ПИН должен быть 14 символов")
    @Schema(description = "ПИН", example = "22212200001155")
    String pin;
    @Email(message = "Введите корректный email")
    @Schema(description = "Почта", example = "test@gmail.com")
    String email;
    @Size(min = 2, message = "Введите корректный ФИО")
    @Schema(description = "ФИО", example = "Уланов Нурдин")
    String fullName;
    @Pattern(regexp = "^\\+996[0-9]{9}$")
    @Size(min = 13, max = 13, message = "Введите корректный номер телефона")
    @Schema(description = "Номер телефона", example = "+996779977288")
    String phoneNumber;
    @Size(min = 2, message = "Введите корректный пароль от 2 символов")
    @Schema(description = "Пароль", example = "qwe!23")
    String password;
}

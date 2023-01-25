package kg.geektech.geektechfinalprojectbackend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationRequestDto {
    @NotNull
    @NotEmpty(message = "ПИН не должен быть пустым")
    @Size(min = 14, max = 14, message = "ПИН должен быть 14 символов")
    String pin;
    @NotNull
    @NotEmpty(message = "Email не должен быть пустым")
    @Email(message = "Введите корректный email")
    String email;
    @NotNull
    @NotEmpty(message = "ФИО не должен быть пустым")
    @Size(min = 2, message = "Введите корректный ФИО")
    String fullName;
    @NotNull
    @Size(min = 2, message = "Введите корректный пароль от 3 символов")
    String password;
}

package kg.geektech.geektechfinalprojectbackend.dto.auth.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Модель данных токена авторизации")
public class AuthDto {
    @Schema(description = "Токен", example = "eyJhbGciOiJIUzI1NiJ9...")
    String token;

    @Schema(description = "Роль пользователя")
    User.Role role;
}

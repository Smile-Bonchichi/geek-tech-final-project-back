package kg.geektech.geektechfinalprojectbackend.dto.auth.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kg.geektech.geektechfinalprojectbackend.dto.BaseResponse;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthResponseDto extends BaseResponse {
    @Schema(description = "Токен", example = "eyJhbGciOiJIUzI1NiJ9...")
    String token;

    @Schema(description = "Роль пользователя")
    User.Role role;
}

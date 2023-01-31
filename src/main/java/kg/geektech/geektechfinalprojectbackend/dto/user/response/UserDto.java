package kg.geektech.geektechfinalprojectbackend.dto.user.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Модель данных пользователя")
public class UserDto {
    @Schema(description = "ПИН", example = "22212200001155")
    String pin;
    @Schema(description = "Почта", example = "test@gmail.com")
    String email;
    @Schema(description = "ФИО", example = "Уланов Нурдин")
    String fullName;
    @Schema(description = "Номер телефона", example = "+996779977288")
    String phoneNumber;
}

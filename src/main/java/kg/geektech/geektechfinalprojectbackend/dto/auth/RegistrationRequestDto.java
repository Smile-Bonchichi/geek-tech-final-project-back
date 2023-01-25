package kg.geektech.geektechfinalprojectbackend.dto.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationRequestDto {
    String pin;
    String email;
    String fullName;
    String password;
}

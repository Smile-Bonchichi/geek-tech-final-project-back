package kg.geektech.geektechfinalprojectbackend.dto.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequestDto {
    String email;
    String password;
}

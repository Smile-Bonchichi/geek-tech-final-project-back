package kg.geektech.geektechfinalprojectbackend.service;

import kg.geektech.geektechfinalprojectbackend.dto.auth.AuthResponseDto;
import kg.geektech.geektechfinalprojectbackend.dto.auth.AuthenticationRequestDto;
import kg.geektech.geektechfinalprojectbackend.dto.auth.RegistrationRequestDto;

public interface AuthService {
    AuthResponseDto login(AuthenticationRequestDto authenticationRequestDto);

    AuthResponseDto register(RegistrationRequestDto registrationRequestDto);
}

package kg.geektech.geektechfinalprojectbackend.service;

import kg.geektech.geektechfinalprojectbackend.dto.auth.response.AuthResponseDto;
import kg.geektech.geektechfinalprojectbackend.dto.auth.request.AuthenticationRequestDto;
import kg.geektech.geektechfinalprojectbackend.dto.auth.request.RegistrationRequestDto;

public interface AuthService {
    AuthResponseDto login(AuthenticationRequestDto authenticationRequestDto);

    void register(RegistrationRequestDto registrationRequestDto);
}

package kg.geektech.geektechfinalprojectbackend.service;

import kg.geektech.geektechfinalprojectbackend.dto.auth.response.AuthDto;
import kg.geektech.geektechfinalprojectbackend.dto.auth.request.AuthenticationDto;
import kg.geektech.geektechfinalprojectbackend.dto.auth.request.RegistrationDto;

public interface AuthService {
    AuthDto login(AuthenticationDto authenticationDto);

    void register(RegistrationDto registrationDto);
}

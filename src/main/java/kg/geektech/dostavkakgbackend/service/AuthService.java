package kg.geektech.dostavkakgbackend.service;

import kg.geektech.dostavkakgbackend.dto.auth.request.AuthenticationDto;
import kg.geektech.dostavkakgbackend.dto.auth.request.RegistrationDto;
import kg.geektech.dostavkakgbackend.dto.auth.response.AuthDto;

public interface AuthService {
    AuthDto login(AuthenticationDto authenticationDto);

    void register(RegistrationDto registrationDto);
}

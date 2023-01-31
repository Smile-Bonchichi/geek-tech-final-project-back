package kg.geektech.dostavkakgbackend.service;

import kg.geektech.dostavkakgbackend.dto.auth.response.AuthDto;
import kg.geektech.dostavkakgbackend.dto.auth.request.AuthenticationDto;
import kg.geektech.dostavkakgbackend.dto.auth.request.RegistrationDto;

public interface AuthService {
    AuthDto login(AuthenticationDto authenticationDto);

    void register(RegistrationDto registrationDto);
}

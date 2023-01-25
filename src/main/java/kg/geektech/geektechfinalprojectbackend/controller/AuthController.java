package kg.geektech.geektechfinalprojectbackend.controller;

import jakarta.validation.Valid;
import kg.geektech.geektechfinalprojectbackend.dto.auth.AuthenticationRequestDto;
import kg.geektech.geektechfinalprojectbackend.dto.auth.RegistrationRequestDto;
import kg.geektech.geektechfinalprojectbackend.service.AuthService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {
    final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequestDto authenticationRequestDto) {
        return ResponseEntity
                .ok(authService.login(authenticationRequestDto));
    }

    @PostMapping("/sign-up")
    ResponseEntity<?> register(@RequestBody @Valid RegistrationRequestDto registrationRequestDto) {
        return ResponseEntity
                .ok(authService.register(registrationRequestDto));
    }
}

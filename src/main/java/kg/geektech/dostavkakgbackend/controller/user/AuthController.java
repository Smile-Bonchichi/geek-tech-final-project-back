package kg.geektech.dostavkakgbackend.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.geektech.dostavkakgbackend.controller.BaseController;
import kg.geektech.dostavkakgbackend.dto.auth.request.AuthenticationDto;
import kg.geektech.dostavkakgbackend.dto.auth.request.RegistrationDto;
import kg.geektech.dostavkakgbackend.dto.auth.response.AuthDto;
import kg.geektech.dostavkakgbackend.service.AuthService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/auth")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Tag(name = "Авторизация")
public class AuthController extends BaseController {
    final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    @Operation(summary = "Авторизаиця", method = "POST")
    @ApiResponse(
            responseCode = "200",
            description = "Успешная авторизация",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthDto.class)
                    )
            })
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDto authenticationDto) {
        return constructSuccessResponse(
                authService.login(authenticationDto)
        );
    }

    @PostMapping("/sign-up")
    @Operation(summary = "Регистрация", method = "POST")
    public void register(@RequestBody @Valid RegistrationDto registrationDto) {
        authService.register(registrationDto);
    }
}

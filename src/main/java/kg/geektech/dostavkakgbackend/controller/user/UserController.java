package kg.geektech.dostavkakgbackend.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.geektech.dostavkakgbackend.controller.BaseController;
import kg.geektech.dostavkakgbackend.dto.BaseResponse;
import kg.geektech.dostavkakgbackend.dto.card.request.AddCardDto;
import kg.geektech.dostavkakgbackend.dto.card.response.CardDto;
import kg.geektech.dostavkakgbackend.dto.user.UpdateUserDto;
import kg.geektech.dostavkakgbackend.dto.user.response.UserDto;
import kg.geektech.dostavkakgbackend.entity.user.User;
import kg.geektech.dostavkakgbackend.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Tag(name = "Пользователь")
@SecurityRequirement(name = "bearerAuth")
public class UserController extends BaseController {
    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/confirm")
    @Operation(summary = "Подтверждение почты", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "400",
                    description = "Не коректные данные",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка на сервере",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class)
                            )
                    })
    })
    public void confirmEmail(@AuthenticationPrincipal User user) {
        userService.confirm(user);
    }

    @PostMapping("/add-card")
    @Operation(summary = "Добавление карты", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное добавление",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CardDto.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не коректные данные",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка на сервере",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class)
                            )
                    })
    })
    public ResponseEntity<?> putCard(@RequestBody @Valid AddCardDto addCardDto,
                                     @AuthenticationPrincipal User user) {
        return constructSuccessResponse(
                userService.putCard(addCardDto, user)
        );
    }

    @GetMapping
    @Operation(summary = "Получение информации", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное получение информации",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не коректные данные",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка на сервере",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class)
                            )
                    })
    })
    public ResponseEntity<?> get(@AuthenticationPrincipal User user) {
        return constructSuccessResponse(
                userService.getUserInfo(user)
        );
    }

    @PutMapping
    @Operation(summary = "Обновление профиля", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное обновление",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UpdateUserDto.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не коректные данные",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка на сервере",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class)
                            )
                    })
    })
    public ResponseEntity<?> updateUser(@RequestBody @Valid UpdateUserDto updateUserDto,
                                        @AuthenticationPrincipal User user) {
        return constructSuccessResponse(
                userService.updateUserInfo(updateUserDto, user)
        );
    }

    @DeleteMapping
    @Operation(summary = "Удаление", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "400",
                    description = "Не коректные данные",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка на сервере",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class)
                            )
                    })
    })
    public void deleteUser(@AuthenticationPrincipal User user) {
        userService.deleteUser(user);
    }
}

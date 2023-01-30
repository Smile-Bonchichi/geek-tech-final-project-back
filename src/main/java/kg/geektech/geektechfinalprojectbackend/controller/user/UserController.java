package kg.geektech.geektechfinalprojectbackend.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.geektech.geektechfinalprojectbackend.dto.BaseResponse;
import kg.geektech.geektechfinalprojectbackend.dto.user.UpdateUserDto;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import kg.geektech.geektechfinalprojectbackend.service.UserService;
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
public class UserController {
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
        return ResponseEntity
                .ok(userService.updateUserInfo(updateUserDto, user));
    }

    @DeleteMapping
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

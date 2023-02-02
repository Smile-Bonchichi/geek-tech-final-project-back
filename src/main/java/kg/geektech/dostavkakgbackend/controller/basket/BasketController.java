package kg.geektech.dostavkakgbackend.controller.basket;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import kg.geektech.dostavkakgbackend.controller.BaseController;
import kg.geektech.dostavkakgbackend.dto.BaseResponse;
import kg.geektech.dostavkakgbackend.dto.basket.BasketDto;
import kg.geektech.dostavkakgbackend.entity.user.User;
import kg.geektech.dostavkakgbackend.service.BasketService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Tag(name = "Корзина")
@SecurityRequirement(name = "bearerAuth")
public class BasketController extends BaseController {
    final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping("/{id}")
    @Operation(summary = "Добавление продукта", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное добавление",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BasketDto.class)
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
    public ResponseEntity<?> add(@PathVariable("id")
                                 @Valid
                                 @NotNull @Min(1)
                                 @Schema(description = "ID продукта")
                                 Long id,
                                 @AuthenticationPrincipal User user) {
        return constructSuccessResponse(
                basketService.put(id, user)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление продукта", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное удаление",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BasketDto.class)
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
    public ResponseEntity<?> delete(@PathVariable("id")
                                    @Valid
                                    @NotNull @Min(1)
                                    @Schema(description = "ID продукта")
                                    Long id,
                                    @AuthenticationPrincipal User user) {
        return constructSuccessResponse(
                basketService.delete(id, user)
        );
    }

    @GetMapping
    @Operation(summary = "Получение", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное получение",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BasketDto.class)
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
    public ResponseEntity<?> getAll(@AuthenticationPrincipal User user) {
        return constructSuccessResponse(
                basketService.getAll(user)
        );
    }
}

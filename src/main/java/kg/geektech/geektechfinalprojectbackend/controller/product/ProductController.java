package kg.geektech.geektechfinalprojectbackend.controller.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import kg.geektech.geektechfinalprojectbackend.dto.BaseResponse;
import kg.geektech.geektechfinalprojectbackend.dto.product.response.FavoriteProductDto;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import kg.geektech.geektechfinalprojectbackend.service.ProductService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Tag(name = "Продукты")
public class ProductController {
    final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/favorite/{id}")
    @Operation(summary = "Добавление избранного", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное добавление",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FavoriteProductDto.class)
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
    public ResponseEntity<?> putFavorite(@PathVariable("id")
                                         @Valid
                                         @NotNull @Min(1) Long id,
                                         @AuthenticationPrincipal User user) {
        return ResponseEntity
                .ok(productService.favorite(id, user));
    }
}

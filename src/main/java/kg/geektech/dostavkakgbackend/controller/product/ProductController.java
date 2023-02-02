package kg.geektech.dostavkakgbackend.controller.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import kg.geektech.dostavkakgbackend.controller.BaseController;
import kg.geektech.dostavkakgbackend.dto.product.request.AddProductDto;
import kg.geektech.dostavkakgbackend.dto.product.request.ChangeProductDto;
import kg.geektech.dostavkakgbackend.dto.product.response.FavoriteProductDto;
import kg.geektech.dostavkakgbackend.dto.product.response.ProductDto;
import kg.geektech.dostavkakgbackend.dto.product.response.ProductInfoDto;
import kg.geektech.dostavkakgbackend.entity.user.User;
import kg.geektech.dostavkakgbackend.service.ProductService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Tag(name = "Продукты")
@SecurityRequirement(name = "bearerAuth")
public class ProductController extends BaseController {
    final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Operation(summary = "Добавление", method = "POST")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное добавление",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class)
                    )
            })
    public ResponseEntity<?> put(@RequestBody @Valid AddProductDto addProductDto,
                                 @AuthenticationPrincipal User user) {
        return constructSuccessResponse(
                productService.put(addProductDto, user)
        );
    }

    @PutMapping
    @Operation(summary = "Обновление", method = "PUT")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное обновление",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class)
                    )
            })
    public ResponseEntity<?> change(@RequestBody @Valid ChangeProductDto changeProductDto,
                                    @AuthenticationPrincipal User user) {
        return constructSuccessResponse(
                productService.change(changeProductDto, user)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление", method = "DELETE")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное удаление",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class)
                    )
            })
    public void delete(@PathVariable("id")
                       @Valid
                       @NotNull @Min(1)
                       @Schema(description = "ID продукта")
                       Long id) {
        productService.delete(id);
    }

    @GetMapping
    @Operation(summary = "Получение", method = "GET")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductInfoDto.class)
                    )
            })
    public ResponseEntity<?> getAll() {
        return constructSuccessResponse(
                productService.getAll()
        );
    }

    @PostMapping("/favorite/{id}")
    @Operation(summary = "Добавление избранного", method = "POST")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное добавление",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FavoriteProductDto.class)
                    )
            })
    public ResponseEntity<?> putFavorite(@PathVariable("id")
                                         @Valid
                                         @NotNull @Min(1)
                                         @Schema(description = "ID продукта")
                                         Long id,
                                         @AuthenticationPrincipal User user) {
        return constructSuccessResponse(
                productService.favorite(id, user)
        );
    }

    @DeleteMapping("/favorite/{id}")
    @Operation(summary = "Удаление избранного", method = "DELETE")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное удаление",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FavoriteProductDto.class)
                    )
            })
    public ResponseEntity<?> deleteFavorite(@PathVariable("id")
                                            @Valid
                                            @NotNull @Min(1)
                                            @Schema(description = "ID продукта")
                                            Long id,
                                            @AuthenticationPrincipal User user) {
        return constructSuccessResponse(
                productService.deleteFavorite(id, user)
        );
    }
}

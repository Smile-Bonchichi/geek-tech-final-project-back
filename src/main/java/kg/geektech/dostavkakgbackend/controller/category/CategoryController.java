package kg.geektech.dostavkakgbackend.controller.category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.geektech.dostavkakgbackend.controller.BaseController;
import kg.geektech.dostavkakgbackend.dto.category.request.CRUDCategoryDto;
import kg.geektech.dostavkakgbackend.dto.category.response.CategoryDto;
import kg.geektech.dostavkakgbackend.entity.user.User;
import kg.geektech.dostavkakgbackend.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Tag(name = "Категория")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController extends BaseController {
    final CategoryService categoryService;

    @PostMapping
    @Operation(summary = "Создание", method = "POST")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное создание",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CategoryDto.class)
                    )
            })
    public ResponseEntity<?> create(@RequestBody @Valid CRUDCategoryDto CRUDCategoryDto,
                                    @AuthenticationPrincipal User user) {
        return constructSuccessResponse(
                categoryService.create(CRUDCategoryDto, user)
        );
    }

    @GetMapping
    @Operation(summary = "Получение по ID", method = "GET")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение по ID",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CategoryDto.class)
                    )
            })
    public ResponseEntity<?> getById(@RequestBody @Valid CRUDCategoryDto CRUDCategoryDto) {
        return constructSuccessResponse(
                categoryService.findById(CRUDCategoryDto)
        );
    }

    @PutMapping
    @Operation(summary = "Изменение по ID", method = "PUT")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное изменение по ID",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CategoryDto.class)
                    )
            })
    public ResponseEntity<?> change(@RequestBody @Valid CRUDCategoryDto CRUDCategoryDto,
                                    @AuthenticationPrincipal User user) {
        return constructSuccessResponse(
                categoryService.change(CRUDCategoryDto, user)
        );
    }

    @DeleteMapping
    @Operation(summary = "Удаление по ID", method = "DELETE")
    public void delete(@RequestBody @Valid CRUDCategoryDto CRUDCategoryDto) {
        categoryService.delete(CRUDCategoryDto);
    }

    @GetMapping("get-all")
    @Operation(summary = "Получение всех", method = "GET")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение всех",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CategoryDto.class)
                    )
            })
    public ResponseEntity<?> getAll() {
        return constructSuccessResponse(
                categoryService.getAll()
        );
    }
}

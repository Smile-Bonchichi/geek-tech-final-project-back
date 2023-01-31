package kg.geektech.dostavkakgbackend.controller.category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.geektech.dostavkakgbackend.controller.BaseController;
import kg.geektech.dostavkakgbackend.dto.BaseResponse;
import kg.geektech.dostavkakgbackend.dto.category.request.CRUDCategoryDto;
import kg.geektech.dostavkakgbackend.dto.category.response.CategoryDto;
import kg.geektech.dostavkakgbackend.entity.user.User;
import kg.geektech.dostavkakgbackend.service.CategoryService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Tag(name = "Категория")
public class CategoryController extends BaseController {
    final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @Operation(summary = "Создание", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное создание",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryDto.class)
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
    public ResponseEntity<?> create(@RequestBody @Valid CRUDCategoryDto CRUDCategoryDto,
                                    @AuthenticationPrincipal User user) {
        return constructSuccessResponse(
                categoryService.create(CRUDCategoryDto, user)
        );
    }

    @GetMapping
    @Operation(summary = "Получение по ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное получение по ID",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryDto.class)
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
    public ResponseEntity<?> getById(@RequestBody @Valid CRUDCategoryDto CRUDCategoryDto) {
        return constructSuccessResponse(
                categoryService.findById(CRUDCategoryDto)
        );
    }

    @PutMapping
    @Operation(summary = "Изменение по ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное изменение по ID",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryDto.class)
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
    public ResponseEntity<?> change(@RequestBody @Valid CRUDCategoryDto CRUDCategoryDto,
                                    @AuthenticationPrincipal User user) {
        return constructSuccessResponse(
                categoryService.change(CRUDCategoryDto, user)
        );
    }

    @DeleteMapping
    @Operation(summary = "Удаление по ID", method = "DELETE")
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
    public void delete(@RequestBody @Valid CRUDCategoryDto CRUDCategoryDto) {
        categoryService.delete(CRUDCategoryDto);
    }

    @GetMapping("get-all")
    @Operation(summary = "Получение всех", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное получение всех",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryDto.class)
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
    public ResponseEntity<?> getAll() {
        return constructSuccessResponse(
                categoryService.getAll()
        );
    }
}

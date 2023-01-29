package kg.geektech.geektechfinalprojectbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import kg.geektech.geektechfinalprojectbackend.dto.BaseResponse;
import kg.geektech.geektechfinalprojectbackend.entity.image.Image;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import kg.geektech.geektechfinalprojectbackend.service.ImageService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Tag(name = "Изображения")
public class ImageController {
    final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "Загрузка изображения", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешная загрузка",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(example = "Ссылка на изображение")
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
    public ResponseEntity<?> load(@RequestParam(name = "image")
                                  @Valid
                                  @NotNull
                                  @Parameter(
                                          description = "Изображение",
                                          content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE)
                                  ) MultipartFile image,
                                  @RequestParam(name = "type")
                                  @Valid
                                  @NotNull
                                  @Parameter(description = "Тип изображения") Image.ImageType type,
                                  @AuthenticationPrincipal User user) {
        return ResponseEntity
                .ok(imageService.loadImage(image, type, user));
    }
}

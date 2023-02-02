package kg.geektech.dostavkakgbackend.controller.image;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import kg.geektech.dostavkakgbackend.controller.BaseController;
import kg.geektech.dostavkakgbackend.dto.image.ImageDto;
import kg.geektech.dostavkakgbackend.entity.image.Image;
import kg.geektech.dostavkakgbackend.service.ImageService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Tag(name = "Изображения")
@SecurityRequirement(name = "bearerAuth")
public class ImageController extends BaseController {
    final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/{id}")
    @Operation(summary = "Загрузка", method = "POST")
    @ApiResponse(
            responseCode = "200",
            description = "Успешная загрузка",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ImageDto.class)
                    )
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
                                  @PathVariable("id")
                                  @Valid
                                  @NotNull @Min(1)
                                  @Schema(description = "ID продукта")
                                  Long id) {
        return constructSuccessResponse(
                imageService.loadImage(image, id, type)
        );
    }
}

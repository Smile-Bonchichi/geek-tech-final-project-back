package kg.geektech.geektechfinalprojectbackend.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import kg.geektech.geektechfinalprojectbackend.entity.image.Image;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import kg.geektech.geektechfinalprojectbackend.service.ImageService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ImageController {
    final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<?> load(@RequestParam(name = "image") @Valid @NotNull MultipartFile image,
                                  @RequestParam(name = "type") @Valid @NotNull Image.ImageType type,
                                  @AuthenticationPrincipal User user) {
        return ResponseEntity
                .ok(imageService.loadImage(image, type, user));
    }
}

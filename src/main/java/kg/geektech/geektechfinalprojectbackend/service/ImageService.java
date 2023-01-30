package kg.geektech.geektechfinalprojectbackend.service;

import kg.geektech.geektechfinalprojectbackend.dto.image.response.ImageResponseDto;
import kg.geektech.geektechfinalprojectbackend.entity.image.Image;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    ImageResponseDto loadImage(MultipartFile image, Image.ImageType type, User user);

    List<ImageResponseDto> getAllImages(Image.ImageType type, User user);
}

package kg.geektech.geektechfinalprojectbackend.service;

import kg.geektech.geektechfinalprojectbackend.dto.image.ImageDto;
import kg.geektech.geektechfinalprojectbackend.entity.image.Image;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    ImageDto loadImage(MultipartFile image, Image.ImageType type, User user);

    List<Image> loadImages(List<MultipartFile> images, Image.ImageType type, User user);

    List<ImageDto> getAllImages(Image.ImageType type, User user);
}

package kg.geektech.geektechfinalprojectbackend.service;

import kg.geektech.geektechfinalprojectbackend.entity.image.Image;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String loadImage(MultipartFile image, Image.ImageType type, User user);
}

package kg.geektech.dostavkakgbackend.service;

import kg.geektech.dostavkakgbackend.dto.image.ImageDto;
import kg.geektech.dostavkakgbackend.entity.image.Image;
import kg.geektech.dostavkakgbackend.entity.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    ImageDto loadImage(MultipartFile image, Image.ImageType type, User user);

    List<Image> loadImages(List<MultipartFile> images, Image.ImageType type, User user);

    List<ImageDto> getAllImages(Image.ImageType type, User user);
}

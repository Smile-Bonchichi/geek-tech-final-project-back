package kg.geektech.dostavkakgbackend.service;

import kg.geektech.dostavkakgbackend.dto.image.ImageDto;
import kg.geektech.dostavkakgbackend.entity.image.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    ImageDto loadImage(MultipartFile image, Long id, Image.ImageType type);
}

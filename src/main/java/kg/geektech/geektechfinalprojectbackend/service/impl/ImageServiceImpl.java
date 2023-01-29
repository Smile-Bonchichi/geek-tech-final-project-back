package kg.geektech.geektechfinalprojectbackend.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kg.geektech.geektechfinalprojectbackend.entity.image.Image;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import kg.geektech.geektechfinalprojectbackend.exception.ImageLoadException;
import kg.geektech.geektechfinalprojectbackend.repository.ImageRepository;
import kg.geektech.geektechfinalprojectbackend.service.ImageService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageServiceImpl implements ImageService {
    final ImageRepository imageRepository;
    final Cloudinary cloudinary;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository,
                            Cloudinary cloudinary) {
        this.imageRepository = imageRepository;
        this.cloudinary = cloudinary;
    }

    @Override
    public String loadImage(MultipartFile image, Image.ImageType type, User user) {
        try {
            File file = Files.createTempFile(
                            String.valueOf(System.currentTimeMillis()),
                            image.getOriginalFilename().substring(image.getOriginalFilename().length() - 4)
                    )
                    .toFile();

            image.transferTo(file);

            Map upload = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

            return imageRepository.save(
                    Image.builder()
                            .type(type)
                            .url((String) upload.get("url"))
                            .user(user)
                            .build()
            ).getUrl();
        } catch (IOException e) {
            throw new ImageLoadException("Не удалось загрузить фотографию", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
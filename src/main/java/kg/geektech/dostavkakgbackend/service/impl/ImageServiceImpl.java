package kg.geektech.dostavkakgbackend.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kg.geektech.dostavkakgbackend.dto.image.ImageDto;
import kg.geektech.dostavkakgbackend.entity.image.Image;
import kg.geektech.dostavkakgbackend.entity.user.User;
import kg.geektech.dostavkakgbackend.exception.image.ImageLoadException;
import kg.geektech.dostavkakgbackend.mapper.ImageMapper;
import kg.geektech.dostavkakgbackend.repository.ImageRepository;
import kg.geektech.dostavkakgbackend.service.ImageService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
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
    public ImageDto loadImage(MultipartFile image, Image.ImageType type, User user) {
        Map upload = loadCloudinary(image);

        return ImageMapper.INSTANCE.imageToImageResponseDto(
                imageRepository.save(
                        Image.builder()
                                .type(type)
                                .url((String) upload.get("url"))
                                .user(user)
                                .build()
                )
        );
    }

    @Override
    public List<Image> loadImages(List<MultipartFile> images, Image.ImageType type, User user) {
        List<Image> imageList = new ArrayList<>();

        for (int i = 0; i < images.size(); i++) {
            Map upload = loadCloudinary(images.get(i));

            imageList.add(
                    imageRepository.save(
                            Image.builder()
                                    .type(type)
                                    .url((String) upload.get("url"))
                                    .user(user)
                                    .build()
                    )
            );
        }

        return imageList;
    }

    private Map loadCloudinary(MultipartFile image) {
        try {
            File file = Files.createTempFile(
                            String.valueOf(System.currentTimeMillis()),
                            image.getOriginalFilename().substring(image.getOriginalFilename().length() - 4)
                    )
                    .toFile();

            image.transferTo(file);

            return cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new ImageLoadException("Не удалось загрузить фотографию", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<ImageDto> getAllImages(Image.ImageType type, User user) {
        return ImageMapper.INSTANCE.imagesToImageResponseDtos(
                imageRepository.findAllByTypeAndUser(type, user)
        );
    }
}

package kg.geektech.dostavkakgbackend.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kg.geektech.dostavkakgbackend.dto.image.ImageDto;
import kg.geektech.dostavkakgbackend.entity.category.Category;
import kg.geektech.dostavkakgbackend.entity.image.Image;
import kg.geektech.dostavkakgbackend.entity.product.Product;
import kg.geektech.dostavkakgbackend.exception.common.NotFoundException;
import kg.geektech.dostavkakgbackend.exception.image.ImageLoadException;
import kg.geektech.dostavkakgbackend.mapper.ImageMapper;
import kg.geektech.dostavkakgbackend.repository.ImageRepository;
import kg.geektech.dostavkakgbackend.service.CategoryService;
import kg.geektech.dostavkakgbackend.service.ImageService;
import kg.geektech.dostavkakgbackend.service.ProductService;
import kg.geektech.dostavkakgbackend.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageServiceImpl implements ImageService {
    final ImageRepository imageRepository;
    final Cloudinary cloudinary;
    final UserService userService;
    final ProductService productService;
    final CategoryService categoryService;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository,
                            Cloudinary cloudinary,
                            UserService userService,
                            ProductService productService,
                            CategoryService categoryService) {
        this.imageRepository = imageRepository;
        this.cloudinary = cloudinary;
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public ImageDto loadImage(MultipartFile image, Long id, Image.ImageType type) {
        Map upload = loadCloudinary(image);

        Image imageDB = imageRepository.save(
                Image.builder()
                        .type(type)
                        .url((String) upload.get("url"))
                        .build()
        );

        switch (type) {
            case AVATAR -> userService.save(userService.getById(id).setImage(imageDB));
            case PRODUCT -> {
                Product product = productService.getById(id);

                List<Image> images = product.getImages();
                images.add(imageDB);
                productService.save(product.setImages(images));
            }
            case CATEGORY -> {
                Category category = categoryService.getById(id);

                List<Image> images = category.getImages();
                images.add(imageDB);
                categoryService.save(category.setImages(images));
            }
            default -> throw new NotFoundException("Такого типа изображения нет", HttpStatus.BAD_REQUEST);
        }

        return ImageMapper.INSTANCE.imageToImageResponseDto(
                imageDB
        );
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
}

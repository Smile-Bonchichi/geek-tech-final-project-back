package kg.geektech.dostavkakgbackend.service.impl;

import kg.geektech.dostavkakgbackend.dto.category.CategoryDto;
import kg.geektech.dostavkakgbackend.entity.category.Category;
import kg.geektech.dostavkakgbackend.entity.image.Image;
import kg.geektech.dostavkakgbackend.entity.user.User;
import kg.geektech.dostavkakgbackend.exception.common.NotFoundException;
import kg.geektech.dostavkakgbackend.mapper.CategoryMapper;
import kg.geektech.dostavkakgbackend.repository.CategoryRepository;
import kg.geektech.dostavkakgbackend.service.CategoryService;
import kg.geektech.dostavkakgbackend.service.ImageService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryServiceImpl implements CategoryService {
    final CategoryRepository categoryRepository;
    final ImageService imageService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ImageService imageService) {
        this.categoryRepository = categoryRepository;
        this.imageService = imageService;
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto, User user) {
        return CategoryMapper.INSTANCE.categoryToCategoryDto(
                categoryRepository.save(
                        Category.builder()
                                .name(categoryDto.getName())
                                .images(
                                        imageService.loadImages(
                                                categoryDto.getImages(),
                                                Image.ImageType.CATEGORY,
                                                user
                                        )
                                )
                                .build()
                )
        );
    }

    @Override
    public CategoryDto delete(CategoryDto categoryDto) {
        Category category = getById(categoryDto.getId());

        categoryRepository.delete(category);

        return CategoryMapper.INSTANCE.categoryToCategoryDto(
                category
        );
    }

    @Override
    public CategoryDto change(CategoryDto categoryDto, User user) {
        Category category = getById(categoryDto.getId());

        return CategoryMapper.INSTANCE.categoryToCategoryDto(
                categoryRepository.save(
                        category
                                .setName(categoryDto.getName() != null ? categoryDto.getName() : category.getName())
                                .setImages(
                                        categoryDto.getImages() != null ?
                                                imageService.loadImages(categoryDto.getImages(), Image.ImageType.CATEGORY, user) :
                                                category.getImages()
                                )
                )
        );
    }

    @Override
    public CategoryDto findById(CategoryDto categoryDto) {
        return CategoryMapper.INSTANCE.categoryToCategoryDto(
                categoryRepository.save(
                        getById(categoryDto.getId())
                )
        );
    }

    @Override
    public List<CategoryDto> getAll() {
        return CategoryMapper.INSTANCE.categoriesToCategoryDtos(
                categoryRepository.findAll()
        );
    }

    @Override
    public List<Category> getAllByIds(List<Long> ids) {
        return categoryRepository.findAllById(ids);
    }

    private Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Такой категории нет", HttpStatus.BAD_REQUEST));
    }
}

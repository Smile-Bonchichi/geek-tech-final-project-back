package kg.geektech.geektechfinalprojectbackend.service.impl;

import kg.geektech.geektechfinalprojectbackend.dto.category.CategoryDto;
import kg.geektech.geektechfinalprojectbackend.entity.category.Category;
import kg.geektech.geektechfinalprojectbackend.exception.category.CategoryNotFoundException;
import kg.geektech.geektechfinalprojectbackend.mapper.CategoryMapper;
import kg.geektech.geektechfinalprojectbackend.repository.CategoryRepository;
import kg.geektech.geektechfinalprojectbackend.service.CategoryService;
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

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        return CategoryMapper.INSTANCE.categoryToCategoryDto(
                categoryRepository.save(
                        Category.builder()
                                .name(categoryDto.getName())
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
    public CategoryDto change(CategoryDto categoryDto) {
        Category category = getById(categoryDto.getId());

        return CategoryMapper.INSTANCE.categoryToCategoryDto(
                categoryRepository.save(
                        category.setName(categoryDto.getName())
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

    private Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Такой категории нет", HttpStatus.BAD_REQUEST));
    }
}

package kg.geektech.dostavkakgbackend.service.impl;

import kg.geektech.dostavkakgbackend.dto.category.request.CRUDCategoryDto;
import kg.geektech.dostavkakgbackend.dto.category.response.CategoryDto;
import kg.geektech.dostavkakgbackend.entity.category.Category;
import kg.geektech.dostavkakgbackend.entity.user.User;
import kg.geektech.dostavkakgbackend.exception.common.NotFoundException;
import kg.geektech.dostavkakgbackend.mapper.ImageMapper;
import kg.geektech.dostavkakgbackend.repository.CategoryRepository;
import kg.geektech.dostavkakgbackend.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryServiceImpl implements CategoryService {
    final CategoryRepository categoryRepository;

    @Override
    public CategoryDto create(CRUDCategoryDto CRUDCategoryDto, User user) {
        Category category = categoryRepository.save(
                Category.builder()
                        .name(CRUDCategoryDto.getName())
                        .build()
        );

        return buildCategoryDto(
                category
        );
    }

    @Override
    public void delete(CRUDCategoryDto CRUDCategoryDto) {
        categoryRepository.delete(getById(CRUDCategoryDto.getId()));
    }

    @Override
    public CategoryDto change(CRUDCategoryDto CRUDCategoryDto, User user) {
        Category category = getById(CRUDCategoryDto.getId());

        category = categoryRepository.save(
                category.setName(
                        CRUDCategoryDto.getName() != null ?
                                CRUDCategoryDto.getName() :
                                category.getName()
                )
        );

        return buildCategoryDto(
                category
        );
    }

    @Override
    public CategoryDto findById(CRUDCategoryDto CRUDCategoryDto) {
        return buildCategoryDto(
                getById(CRUDCategoryDto.getId())
        );
    }

    @Override
    public List<CategoryDto> getAll() {
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (Category category : categoryRepository.findAll()) {
            categoryDtos.add(
                    buildCategoryDto(category)
            );
        }

        return categoryDtos;
    }

    @Override
    public List<Category> getAllByIds(List<Long> ids) {
        return categoryRepository.findAllById(ids);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Такой категории нет"));
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    private CategoryDto buildCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .imageDtos(ImageMapper.INSTANCE.imagesToImageResponseDtos(category.getImages()))
                .build();
    }
}

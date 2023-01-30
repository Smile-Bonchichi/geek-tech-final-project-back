package kg.geektech.geektechfinalprojectbackend.service;

import kg.geektech.geektechfinalprojectbackend.dto.category.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryDto categoryDto);

    CategoryDto delete(CategoryDto categoryDto);

    CategoryDto change(CategoryDto categoryDto);

    CategoryDto findById(CategoryDto categoryDto);

    List<CategoryDto> getAll();
}

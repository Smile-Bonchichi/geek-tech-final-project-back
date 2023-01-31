package kg.geektech.geektechfinalprojectbackend.service;

import kg.geektech.geektechfinalprojectbackend.dto.category.CategoryDto;
import kg.geektech.geektechfinalprojectbackend.entity.category.Category;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryDto categoryDto, User user);

    CategoryDto delete(CategoryDto categoryDto);

    CategoryDto change(CategoryDto categoryDto, User user);

    CategoryDto findById(CategoryDto categoryDto);

    List<CategoryDto> getAll();

    List<Category> getAllByIds(List<Long> ids);
}

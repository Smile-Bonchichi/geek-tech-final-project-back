package kg.geektech.dostavkakgbackend.service;

import kg.geektech.dostavkakgbackend.dto.category.CategoryDto;
import kg.geektech.dostavkakgbackend.entity.category.Category;
import kg.geektech.dostavkakgbackend.entity.user.User;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryDto categoryDto, User user);

    CategoryDto delete(CategoryDto categoryDto);

    CategoryDto change(CategoryDto categoryDto, User user);

    CategoryDto findById(CategoryDto categoryDto);

    List<CategoryDto> getAll();

    List<Category> getAllByIds(List<Long> ids);
}

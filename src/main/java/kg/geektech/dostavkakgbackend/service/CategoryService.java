package kg.geektech.dostavkakgbackend.service;

import kg.geektech.dostavkakgbackend.dto.category.request.CRUDCategoryDto;
import kg.geektech.dostavkakgbackend.dto.category.response.CategoryDto;
import kg.geektech.dostavkakgbackend.entity.category.Category;
import kg.geektech.dostavkakgbackend.entity.user.User;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CRUDCategoryDto CRUDCategoryDto, User user);

    void delete(CRUDCategoryDto CRUDCategoryDto);

    CategoryDto change(CRUDCategoryDto CRUDCategoryDto, User user);

    CategoryDto findById(CRUDCategoryDto CRUDCategoryDto);

    List<CategoryDto> getAll();

    List<Category> getAllByIds(List<Long> ids);

    Category getById(Long id);

    void save(Category category);
}

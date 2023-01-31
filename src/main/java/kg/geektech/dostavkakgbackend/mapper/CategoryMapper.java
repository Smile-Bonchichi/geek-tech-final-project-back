package kg.geektech.dostavkakgbackend.mapper;

import kg.geektech.dostavkakgbackend.dto.category.CategoryDto;
import kg.geektech.dostavkakgbackend.entity.category.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categoryToCategoryDto(Category category);

    List<CategoryDto> categoriesToCategoryDtos(List<Category> categories);
}

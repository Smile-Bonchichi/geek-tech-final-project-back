package kg.geektech.geektechfinalprojectbackend.service;

import kg.geektech.geektechfinalprojectbackend.dto.product.response.FavoriteProductDto;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;

public interface ProductService {
    FavoriteProductDto favorite(Long id, User user);
}

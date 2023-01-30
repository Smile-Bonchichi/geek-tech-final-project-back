package kg.geektech.geektechfinalprojectbackend.service;

import kg.geektech.geektechfinalprojectbackend.dto.product.request.AddProductDto;
import kg.geektech.geektechfinalprojectbackend.dto.product.request.ChangeProductDto;
import kg.geektech.geektechfinalprojectbackend.dto.product.response.FavoriteProductDto;
import kg.geektech.geektechfinalprojectbackend.dto.product.response.ProductDto;
import kg.geektech.geektechfinalprojectbackend.dto.product.response.ProductInfoDto;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;

import java.util.List;

public interface ProductService {
    ProductDto put(AddProductDto addProductDto, User user);

    ProductDto change(ChangeProductDto changeProductDto, User user);

    ProductDto delete(Long id);

    List<ProductInfoDto> getAll();

    FavoriteProductDto favorite(Long id, User user);
    FavoriteProductDto deleteFavorite(Long id, User user);
}

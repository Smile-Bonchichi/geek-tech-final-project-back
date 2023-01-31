package kg.geektech.dostavkakgbackend.service;

import kg.geektech.dostavkakgbackend.dto.product.request.AddProductDto;
import kg.geektech.dostavkakgbackend.dto.product.request.ChangeProductDto;
import kg.geektech.dostavkakgbackend.dto.product.response.FavoriteProductDto;
import kg.geektech.dostavkakgbackend.dto.product.response.ProductInfoDto;
import kg.geektech.dostavkakgbackend.entity.product.Product;
import kg.geektech.dostavkakgbackend.entity.user.User;

import java.util.List;

public interface ProductService {
    ProductInfoDto put(AddProductDto addProductDto, User user);

    ProductInfoDto change(ChangeProductDto changeProductDto, User user);

    void delete(Long id);

    List<ProductInfoDto> getAll();

    Product getById(Long id);

    List<Product> getAllById(List<Long> id);

    void save(Product product);

    FavoriteProductDto favorite(Long id, User user);

    FavoriteProductDto deleteFavorite(Long id, User user);
}

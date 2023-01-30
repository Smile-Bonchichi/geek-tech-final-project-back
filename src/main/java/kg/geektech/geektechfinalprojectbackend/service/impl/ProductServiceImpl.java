package kg.geektech.geektechfinalprojectbackend.service.impl;

import kg.geektech.geektechfinalprojectbackend.dto.product.response.FavoriteProductDto;
import kg.geektech.geektechfinalprojectbackend.entity.product.FavoriteProduct;
import kg.geektech.geektechfinalprojectbackend.entity.product.Product;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import kg.geektech.geektechfinalprojectbackend.exception.common.NotFoundException;
import kg.geektech.geektechfinalprojectbackend.repository.FavoriteProductRepository;
import kg.geektech.geektechfinalprojectbackend.repository.ProductRepository;
import kg.geektech.geektechfinalprojectbackend.service.ProductService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {
    final ProductRepository productRepository;
    final FavoriteProductRepository favoriteProductRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              FavoriteProductRepository favoriteProductRepository) {
        this.productRepository = productRepository;
        this.favoriteProductRepository = favoriteProductRepository;
    }

    @Override
    public FavoriteProductDto favorite(Long id, User user) {
        FavoriteProduct favoriteProduct = favoriteProductRepository.save(
                FavoriteProduct.builder()
                        .product(getById(id))
                        .user(user)
                        .build()
        );

        return FavoriteProductDto.builder()
                .name(favoriteProduct.getProduct().getName())
                .build();
    }

    private Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Такого продукта нет", HttpStatus.BAD_REQUEST));
    }
}

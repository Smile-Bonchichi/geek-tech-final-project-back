package kg.geektech.geektechfinalprojectbackend.service.impl;

import kg.geektech.geektechfinalprojectbackend.dto.product.request.AddProductDto;
import kg.geektech.geektechfinalprojectbackend.dto.product.request.ChangeProductDto;
import kg.geektech.geektechfinalprojectbackend.dto.product.response.FavoriteProductDto;
import kg.geektech.geektechfinalprojectbackend.dto.product.response.ProductDto;
import kg.geektech.geektechfinalprojectbackend.dto.product.response.ProductInfoDto;
import kg.geektech.geektechfinalprojectbackend.entity.image.Image;
import kg.geektech.geektechfinalprojectbackend.entity.product.FavoriteProduct;
import kg.geektech.geektechfinalprojectbackend.entity.product.Product;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import kg.geektech.geektechfinalprojectbackend.exception.common.NotFoundException;
import kg.geektech.geektechfinalprojectbackend.mapper.CategoryMapper;
import kg.geektech.geektechfinalprojectbackend.mapper.ImageMapper;
import kg.geektech.geektechfinalprojectbackend.mapper.ProductMapper;
import kg.geektech.geektechfinalprojectbackend.repository.CategoryRepository;
import kg.geektech.geektechfinalprojectbackend.repository.FavoriteProductRepository;
import kg.geektech.geektechfinalprojectbackend.repository.ProductRepository;
import kg.geektech.geektechfinalprojectbackend.service.ImageService;
import kg.geektech.geektechfinalprojectbackend.service.ProductService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {
    final ProductRepository productRepository;
    final FavoriteProductRepository favoriteProductRepository;
    final CategoryRepository categoryRepository;
    final ImageService imageService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              FavoriteProductRepository favoriteProductRepository,
                              CategoryRepository categoryRepository,
                              ImageService imageService) {
        this.productRepository = productRepository;
        this.favoriteProductRepository = favoriteProductRepository;
        this.categoryRepository = categoryRepository;
        this.imageService = imageService;
    }

    @Override
    public ProductDto put(AddProductDto addProductDto, User user) {
        return ProductMapper.INSTANCE.productToPutProductDto(
                productRepository.save(
                        Product.builder()
                                .name(addProductDto.getName())
                                .description(addProductDto.getDescription())
                                .price(addProductDto.getPrice())
                                .categories(categoryRepository.findAllById(addProductDto.getCategoryId()))
                                .images(imageService.loadImages(addProductDto.getImages(), Image.ImageType.PRODUCT, user))
                                .isPresent(true)
                                .build()
                )
        );
    }

    @Override
    public ProductDto change(ChangeProductDto changeProductDto, User user) {
        Product product = getById(changeProductDto.getId());

        return ProductMapper.INSTANCE.productToPutProductDto(
                productRepository.save(
                        product
                                .setName(changeProductDto.getName() != null ? changeProductDto.getName() : product.getName())
                                .setDescription(
                                        changeProductDto.getDescription() != null ?
                                                changeProductDto.getDescription() :
                                                product.getDescription()
                                )
                                .setPrice(changeProductDto.getPrice() != null ? changeProductDto.getPrice() : product.getPrice())
                                .setCategories(
                                        changeProductDto.getCategoryId() != null ?
                                                categoryRepository.findAllById(changeProductDto.getCategoryId()) :
                                                product.getCategories()
                                )
                                .setImages(
                                        changeProductDto.getImages() != null ?
                                                imageService.loadImages(changeProductDto.getImages(), Image.ImageType.PRODUCT, user) :
                                                product.getImages()
                                )
                )
        );
    }

    @Override
    public ProductDto delete(Long id) {
        Product product = getById(id);
        productRepository.delete(product);

        return ProductMapper.INSTANCE.productToPutProductDto(
                product
        );
    }

    @Override
    public List<ProductInfoDto> getAll() {
        List<ProductInfoDto> productInfoDtos = new ArrayList<>();

        productRepository.findAll()
                .forEach(x -> productInfoDtos.add(
                                ProductInfoDto.builder()
                                        .name(x.getName())
                                        .description(x.getDescription())
                                        .price(x.getPrice())
                                        .categoryDtos(
                                                CategoryMapper.INSTANCE.categoriesToCategoryDtos(x.getCategories())
                                        )
                                        .imageDtos(
                                                ImageMapper.INSTANCE.imagesToImageResponseDtos(x.getImages())
                                        )
                                        .build()
                        )
                );

        return productInfoDtos;
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

    @Override
    public FavoriteProductDto deleteFavorite(Long id, User user) {
        Product product = getById(id);

        favoriteProductRepository.delete(
                favoriteProductRepository.findByProductAndUser(product, user)
                        .orElseThrow()
        );

        return FavoriteProductDto.builder()
                .name(product.getName())
                .build();
    }

    private Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Такого продукта нет", HttpStatus.BAD_REQUEST));
    }
}

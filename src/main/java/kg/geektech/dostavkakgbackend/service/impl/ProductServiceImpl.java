package kg.geektech.dostavkakgbackend.service.impl;

import kg.geektech.dostavkakgbackend.dto.category.response.CategoryDto;
import kg.geektech.dostavkakgbackend.dto.product.request.AddProductDto;
import kg.geektech.dostavkakgbackend.dto.product.request.ChangeProductDto;
import kg.geektech.dostavkakgbackend.dto.product.response.FavoriteProductDto;
import kg.geektech.dostavkakgbackend.dto.product.response.ProductInfoDto;
import kg.geektech.dostavkakgbackend.entity.product.FavoriteProduct;
import kg.geektech.dostavkakgbackend.entity.product.Product;
import kg.geektech.dostavkakgbackend.entity.user.User;
import kg.geektech.dostavkakgbackend.exception.common.NotFoundException;
import kg.geektech.dostavkakgbackend.mapper.ImageMapper;
import kg.geektech.dostavkakgbackend.repository.FavoriteProductRepository;
import kg.geektech.dostavkakgbackend.repository.ProductRepository;
import kg.geektech.dostavkakgbackend.service.CategoryService;
import kg.geektech.dostavkakgbackend.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {
    final ProductRepository productRepository;
    final FavoriteProductRepository favoriteProductRepository;
    final CategoryService categoryService;

    @Override
    public ProductInfoDto put(AddProductDto addProductDto, User user) {
        return buildProductInfoDto(
                productRepository.save(
                        Product.builder()
                                .name(addProductDto.getName())
                                .description(addProductDto.getDescription())
                                .price(addProductDto.getPrice())
                                .categories(categoryService.getAllByIds(addProductDto.getCategoryId()))
                                .isPresent(true)
                                .build()
                )
        );
    }

    @Override
    public ProductInfoDto change(ChangeProductDto changeProductDto, User user) {
        Product product = getById(changeProductDto.getId());

        return buildProductInfoDto(
                productRepository.save(
                        product
                                .setName(
                                        changeProductDto.getName() != null ?
                                                changeProductDto.getName() :
                                                product.getName()
                                )
                                .setDescription(
                                        changeProductDto.getDescription() != null ?
                                                changeProductDto.getDescription() :
                                                product.getDescription()
                                )
                                .setPrice(
                                        changeProductDto.getPrice() != null ?
                                                changeProductDto.getPrice() :
                                                product.getPrice()
                                )
                                .setIsPresent(
                                        changeProductDto.getIsPresent() != null ?
                                                changeProductDto.getIsPresent() :
                                                product.getIsPresent()
                                )
                                .setCategories(
                                        changeProductDto.getCategoryId() != null ?
                                                categoryService.getAllByIds(changeProductDto.getCategoryId()) :
                                                product.getCategories()
                                )
                )
        );
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(getById(id));
    }

    @Override
    public List<ProductInfoDto> getAll() {
        return productRepository.findAll().stream()
                .map(this::buildProductInfoDto)
                .collect(Collectors.toList());
    }

    private ProductInfoDto buildProductInfoDto(Product product) {
        return ProductInfoDto.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryDtos(
                        product.getCategories().stream()
                                .map(c -> CategoryDto.builder()
                                        .id(c.getId())
                                        .name(c.getName())
                                        .build()
                                )
                                .collect(Collectors.toList())
                )
                .imageDtos(
                        ImageMapper.INSTANCE.imagesToImageResponseDtos(product.getImages())
                )
                .build();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Такого продукта нет"));
    }

    @Override
    public List<Product> getAllById(List<Long> id) {
        return productRepository.findAllById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public FavoriteProductDto favorite(Long id, User user) {
        FavoriteProduct favoriteProduct = favoriteProductRepository.save(
                FavoriteProduct.builder()
                        .product(getById(id))
                        .user(user)
                        .build()
        );

        return buildFavoriteProductDto(
                favoriteProduct.getProduct()
        );
    }

    @Override
    public FavoriteProductDto deleteFavorite(Long id, User user) {
        Product product = getById(id);

        favoriteProductRepository.delete(
                favoriteProductRepository.findByProductAndUser(product, user)
                        .orElseThrow(() -> new NotFoundException("Такого избранного товара нет"))
        );

        return buildFavoriteProductDto(
                product
        );
    }

    private FavoriteProductDto buildFavoriteProductDto(Product product) {
        return FavoriteProductDto.builder()
                .name(product.getName())
                .build();
    }
}

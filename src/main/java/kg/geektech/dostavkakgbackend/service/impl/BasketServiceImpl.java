package kg.geektech.dostavkakgbackend.service.impl;

import kg.geektech.dostavkakgbackend.dto.basket.BasketDto;
import kg.geektech.dostavkakgbackend.entity.basket.Basket;
import kg.geektech.dostavkakgbackend.entity.user.User;
import kg.geektech.dostavkakgbackend.exception.common.NotFoundException;
import kg.geektech.dostavkakgbackend.mapper.BasketMapper;
import kg.geektech.dostavkakgbackend.repository.BasketRepository;
import kg.geektech.dostavkakgbackend.service.BasketService;
import kg.geektech.dostavkakgbackend.service.ProductService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BasketServiceImpl implements BasketService {
    final BasketRepository basketRepository;

    final ProductService productService;

    @Autowired
    public BasketServiceImpl(BasketRepository basketRepository,
                             ProductService productService) {
        this.basketRepository = basketRepository;
        this.productService = productService;
    }

    @Override
    public BasketDto put(Long id, User user) {
        return BasketMapper.INSTANCE.basketToBasketDto(
                basketRepository.save(
                        Basket.builder()
                                .product(productService.getById(id))
                                .user(user)
                                .build()
                )
        );
    }

    @Override
    public BasketDto delete(Long id, User user) {
        Basket basket = basketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Такого продукта нет в корзине", HttpStatus.BAD_REQUEST));

        basketRepository.delete(basket);

        return BasketMapper.INSTANCE.basketToBasketDto(
                basket
        );
    }

    @Override
    public List<BasketDto> getAll(User user) {
        return BasketMapper.INSTANCE.basketsToBasketDtos(
                basketRepository.findAllByUser(user)
        );
    }
}

package kg.geektech.dostavkakgbackend.service;

import kg.geektech.dostavkakgbackend.dto.basket.BasketDto;
import kg.geektech.dostavkakgbackend.entity.user.User;

import java.util.List;

public interface BasketService {
    BasketDto put(Long id, User user);

    BasketDto delete(Long id, User user);

    List<BasketDto> getAll(User user);
}

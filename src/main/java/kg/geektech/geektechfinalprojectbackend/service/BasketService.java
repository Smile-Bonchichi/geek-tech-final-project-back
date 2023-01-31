package kg.geektech.geektechfinalprojectbackend.service;

import kg.geektech.geektechfinalprojectbackend.dto.basket.BasketDto;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;

import java.util.List;

public interface BasketService {
    BasketDto put(Long id, User user);

    BasketDto delete(Long id, User user);

    List<BasketDto> getAll(User user);
}

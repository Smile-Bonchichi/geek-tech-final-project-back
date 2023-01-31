package kg.geektech.dostavkakgbackend.service;

import kg.geektech.dostavkakgbackend.dto.order.request.AddOrderDto;
import kg.geektech.dostavkakgbackend.entity.user.User;

public interface OrderService {
    void add(AddOrderDto addOrderDto, User user);

    void confirm(User user);
}

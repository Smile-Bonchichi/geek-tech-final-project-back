package kg.geektech.dostavkakgbackend.repository;

import kg.geektech.dostavkakgbackend.entity.basket.Basket;
import kg.geektech.dostavkakgbackend.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findAllByUser(User user);
}

package kg.geektech.geektechfinalprojectbackend.repository;

import kg.geektech.geektechfinalprojectbackend.entity.basket.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
}

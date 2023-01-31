package kg.geektech.dostavkakgbackend.repository;

import kg.geektech.dostavkakgbackend.entity.order.Order;
import kg.geektech.dostavkakgbackend.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUserAndIsAcceptFalse(User user);
}

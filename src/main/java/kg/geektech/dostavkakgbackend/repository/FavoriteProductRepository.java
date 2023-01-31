package kg.geektech.dostavkakgbackend.repository;

import kg.geektech.dostavkakgbackend.entity.product.FavoriteProduct;
import kg.geektech.dostavkakgbackend.entity.product.Product;
import kg.geektech.dostavkakgbackend.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long> {
    Optional<FavoriteProduct> findByProductAndUser(Product product, User user);
}

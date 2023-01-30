package kg.geektech.geektechfinalprojectbackend.repository;

import kg.geektech.geektechfinalprojectbackend.entity.product.FavoriteProduct;
import kg.geektech.geektechfinalprojectbackend.entity.product.Product;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long> {
    Optional<FavoriteProduct> findByProductAndUser(Product product, User user);
}

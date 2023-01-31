package kg.geektech.dostavkakgbackend.repository;

import kg.geektech.dostavkakgbackend.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

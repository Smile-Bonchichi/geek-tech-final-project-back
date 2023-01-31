package kg.geektech.dostavkakgbackend.repository;

import kg.geektech.dostavkakgbackend.entity.product.PromotionalProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionalProductRepository extends JpaRepository<PromotionalProduct, Long> {
}

package kg.geektech.geektechfinalprojectbackend.repository;

import kg.geektech.geektechfinalprojectbackend.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

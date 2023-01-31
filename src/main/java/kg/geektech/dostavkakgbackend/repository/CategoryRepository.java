package kg.geektech.dostavkakgbackend.repository;

import kg.geektech.dostavkakgbackend.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}

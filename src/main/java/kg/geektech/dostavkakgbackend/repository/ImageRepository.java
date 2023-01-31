package kg.geektech.dostavkakgbackend.repository;

import kg.geektech.dostavkakgbackend.entity.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}

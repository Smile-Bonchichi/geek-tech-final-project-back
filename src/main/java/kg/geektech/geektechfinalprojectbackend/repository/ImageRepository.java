package kg.geektech.geektechfinalprojectbackend.repository;

import kg.geektech.geektechfinalprojectbackend.entity.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}

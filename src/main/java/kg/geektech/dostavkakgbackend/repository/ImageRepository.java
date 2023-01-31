package kg.geektech.dostavkakgbackend.repository;

import kg.geektech.dostavkakgbackend.entity.image.Image;
import kg.geektech.dostavkakgbackend.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByTypeAndUser(Image.ImageType type, User user);
}

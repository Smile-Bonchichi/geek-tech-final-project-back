package kg.geektech.dostavkakgbackend.repository;

import kg.geektech.dostavkakgbackend.entity.card.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCardRepository extends JpaRepository<UserCard, Long> {
}

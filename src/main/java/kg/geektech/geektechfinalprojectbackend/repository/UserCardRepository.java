package kg.geektech.geektechfinalprojectbackend.repository;

import kg.geektech.geektechfinalprojectbackend.entity.card.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCardRepository extends JpaRepository<UserCard, Long> {
}

package kg.geektech.dostavkakgbackend.entity.card;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kg.geektech.dostavkakgbackend.entity.BaseEntity;
import kg.geektech.dostavkakgbackend.entity.user.User;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Builder
@Table(name = "user_card")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCard extends BaseEntity {
    @NotNull
    @Column(name = "card_number", unique = true)
    String cardNumber;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
}

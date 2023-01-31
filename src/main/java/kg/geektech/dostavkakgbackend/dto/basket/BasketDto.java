package kg.geektech.dostavkakgbackend.dto.basket;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Модель данных корзины")
public class BasketDto {
    Long id;

    String name;
}

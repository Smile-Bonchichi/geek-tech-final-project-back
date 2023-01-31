package kg.geektech.dostavkakgbackend.dto.order.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import kg.geektech.dostavkakgbackend.dto.BaseRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Модель данных заказа")
public class AddOrderDto extends BaseRequest {
    @NotNull
    @Schema(description = "ID продуктов", example = "[1, 2, 3]")
    List<Long> ids;
}

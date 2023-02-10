package kg.geektech.dostavkakgbackend.controller.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.geektech.dostavkakgbackend.controller.BaseController;
import kg.geektech.dostavkakgbackend.dto.order.request.AddOrderDto;
import kg.geektech.dostavkakgbackend.entity.user.User;
import kg.geektech.dostavkakgbackend.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Tag(name = "Заказы")
@SecurityRequirement(name = "bearerAuth")
public class OrderController extends BaseController {
    final OrderService orderService;

    @PostMapping
    @Operation(summary = "Добавление", method = "POST")
    public void add(@RequestBody @Valid AddOrderDto addOrderDto,
                    @AuthenticationPrincipal User user) {
        orderService.add(addOrderDto, user);
    }

    @GetMapping("/confirm")
    @Operation(summary = "Подтверждение заказа", method = "GET")
    public void confirm(@AuthenticationPrincipal User user) {
        orderService.confirm(user);
    }
}

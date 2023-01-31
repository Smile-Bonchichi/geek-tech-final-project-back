package kg.geektech.dostavkakgbackend.service.impl;

import kg.geektech.dostavkakgbackend.config.security.JwtService;
import kg.geektech.dostavkakgbackend.dto.order.request.AddOrderDto;
import kg.geektech.dostavkakgbackend.entity.order.Order;
import kg.geektech.dostavkakgbackend.entity.user.User;
import kg.geektech.dostavkakgbackend.repository.OrderRepository;
import kg.geektech.dostavkakgbackend.service.OrderService;
import kg.geektech.dostavkakgbackend.service.ProductService;
import kg.geektech.dostavkakgbackend.util.CommonUtil;
import kg.geektech.dostavkakgbackend.util.MailSenderUtil;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderServiceImpl implements OrderService {
    final OrderRepository orderRepository;
    final ProductService productService;
    final MailSenderUtil mailSenderUtil;
    final CommonUtil commonUtil;
    final JwtService jwtService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductService productService,
                            MailSenderUtil mailSenderUtil,
                            CommonUtil commonUtil,
                            JwtService jwtService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.mailSenderUtil = mailSenderUtil;
        this.commonUtil = commonUtil;
        this.jwtService = jwtService;
    }

    @Override
    public void add(AddOrderDto addOrderDto, User user) {
        Order order = orderRepository.save(
                Order.builder()
                        .products(productService.getAllById(addOrderDto.getIds()))
                        .user(user)
                        .isAccept(false)
                        .build()
        );

        sendConfirmToEmail(
                order.getUser().getEmail(),
                jwtService.generateToken(order.getUser())
        );
    }

    private void sendConfirmToEmail(String email, String token) {
        mailSenderUtil.send(
                email,
                "Подтверждение заказа",
                commonUtil.buildConfirmEmailText(token)
        );
    }

    @Override
    public void confirm(User user) {
        orderRepository.save(
                orderRepository.findByUserAndIsAcceptFalse(user)
                        .setIsAccept(true)
        );
    }
}

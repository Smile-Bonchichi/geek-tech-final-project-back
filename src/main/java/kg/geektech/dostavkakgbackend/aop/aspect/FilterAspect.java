package kg.geektech.dostavkakgbackend.aop.aspect;

import kg.geektech.dostavkakgbackend.config.security.JwtService;
import kg.geektech.dostavkakgbackend.entity.user.User;
import kg.geektech.dostavkakgbackend.exception.common.NotFoundException;
import kg.geektech.dostavkakgbackend.exception.user.UserNotActivityException;
import kg.geektech.dostavkakgbackend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilterAspect {
    final JwtService jwtService;
    final UserRepository userRepository;

    @Autowired
    public FilterAspect(JwtService jwtService,
                        UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Pointcut(value = "@annotation(kg.geektech.dostavkakgbackend.aop.Filter)")
    public void annotatedMethod() {
    }

    @Before("annotatedMethod()")
    public void check() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        String token = attributes.getRequest().getHeader("Authorization");

        String email = jwtService.extractUsername(token.substring(7));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден", HttpStatus.BAD_REQUEST));

        if (!user.isEnabled())
            throw new UserNotActivityException("Пользователь не активирован", HttpStatus.BAD_REQUEST);
    }
}

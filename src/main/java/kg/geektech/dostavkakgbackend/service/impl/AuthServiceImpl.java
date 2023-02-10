package kg.geektech.dostavkakgbackend.service.impl;

import kg.geektech.dostavkakgbackend.config.security.JwtService;
import kg.geektech.dostavkakgbackend.dto.auth.request.AuthenticationDto;
import kg.geektech.dostavkakgbackend.dto.auth.request.RegistrationDto;
import kg.geektech.dostavkakgbackend.dto.auth.response.AuthDto;
import kg.geektech.dostavkakgbackend.entity.user.User;
import kg.geektech.dostavkakgbackend.repository.UserRepository;
import kg.geektech.dostavkakgbackend.service.AuthService;
import kg.geektech.dostavkakgbackend.util.CommonUtil;
import kg.geektech.dostavkakgbackend.util.MailSenderUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {
    final UserRepository userRepository;
    final JwtService jwtService;
    final PasswordEncoder passwordEncoder;
    final AuthenticationManager authenticationManager;
    final MailSenderUtil mailSenderUtil;
    final CommonUtil commonUtil;

    @Override
    public AuthDto login(AuthenticationDto authenticationDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDto.getEmail(),
                        authenticationDto.getPassword()
                )
        );

        User user = (User) authenticate.getPrincipal();

        return AuthDto.builder()
                .token(jwtService.generateToken(user))
                .role(user.getRole())
                .build();
    }

    @Override
    public void register(RegistrationDto registrationDto) {
        User user = userRepository.save(
                User.builder()
                        .pin(registrationDto.getPin())
                        .fullName(registrationDto.getFullName())
                        .email(registrationDto.getEmail())
                        .phoneNumber(registrationDto.getPhoneNumber())
                        .password(passwordEncoder.encode(registrationDto.getPassword()))
                        .role(
                                registrationDto.getFullName().equalsIgnoreCase("admin") ?
                                        User.Role.ROLE_ADMIN :
                                        User.Role.ROLE_USER
                        )
                        .enabled(false)
                        .build()
        );

        mailSenderUtil.send(
                user.getEmail(),
                "Подтверждение почты",
                commonUtil.buildConfirmEmailText(jwtService.generateToken(user))
        );
    }
}

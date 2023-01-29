package kg.geektech.geektechfinalprojectbackend.service.impl;

import kg.geektech.geektechfinalprojectbackend.config.security.JwtService;
import kg.geektech.geektechfinalprojectbackend.dto.auth.request.AuthenticationRequestDto;
import kg.geektech.geektechfinalprojectbackend.dto.auth.request.RegistrationRequestDto;
import kg.geektech.geektechfinalprojectbackend.dto.auth.response.AuthResponseDto;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import kg.geektech.geektechfinalprojectbackend.repository.UserRepository;
import kg.geektech.geektechfinalprojectbackend.service.AuthService;
import kg.geektech.geektechfinalprojectbackend.util.CommonUtil;
import kg.geektech.geektechfinalprojectbackend.util.MailSenderUtil;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {
    final UserRepository userRepository;
    final JwtService jwtService;
    final PasswordEncoder passwordEncoder;
    final AuthenticationManager authenticationManager;
    final MailSenderUtil mailSenderUtil;
    final CommonUtil commonUtil;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           JwtService jwtService,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           MailSenderUtil mailSenderUtil,
                           CommonUtil commonUtil) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.mailSenderUtil = mailSenderUtil;
        this.commonUtil = commonUtil;
    }

    @Override
    public AuthResponseDto login(AuthenticationRequestDto authenticationRequestDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDto.getEmail(),
                        authenticationRequestDto.getPassword()
                )
        );

        User user = (User) authenticate.getPrincipal();

        return AuthResponseDto.builder()
                .token(jwtService.generateToken(user))
                .role(user.getRole())
                .build();
    }

    @Override
    public void register(RegistrationRequestDto registrationRequestDto) {
        User user = userRepository.save(
                User.builder()
                        .pin(registrationRequestDto.getPin())
                        .fullName(registrationRequestDto.getFullName())
                        .email(registrationRequestDto.getEmail())
                        .password(passwordEncoder.encode(registrationRequestDto.getPassword()))
                        .role(
                                registrationRequestDto.getFullName().equalsIgnoreCase("admin") ?
                                        User.Role.ADMIN :
                                        User.Role.USER
                        )
                        .enabled(false)
                        .build()
        );

        sendConfirmToEmail(
                user.getEmail(),
                jwtService.generateToken(user)
        );
    }

    private void sendConfirmToEmail(String email, String token) {
        mailSenderUtil.send(
                email,
                "Подтверждение почты",
                commonUtil.buildConfirmEmailText(token)
        );
    }
}

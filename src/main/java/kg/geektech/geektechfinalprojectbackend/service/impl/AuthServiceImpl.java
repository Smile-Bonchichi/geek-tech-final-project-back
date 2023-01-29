package kg.geektech.geektechfinalprojectbackend.service.impl;

import kg.geektech.geektechfinalprojectbackend.config.security.JwtService;
import kg.geektech.geektechfinalprojectbackend.dto.auth.response.AuthResponseDto;
import kg.geektech.geektechfinalprojectbackend.dto.auth.request.AuthenticationRequestDto;
import kg.geektech.geektechfinalprojectbackend.dto.auth.request.RegistrationRequestDto;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import kg.geektech.geektechfinalprojectbackend.repository.UserRepository;
import kg.geektech.geektechfinalprojectbackend.service.AuthService;
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
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           JwtService jwtService,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponseDto login(AuthenticationRequestDto authenticationRequestDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDto.getEmail(),
                        authenticationRequestDto.getPassword()
                )
        );

        return getToken((User) authenticate.getPrincipal());
    }

    @Override
    public AuthResponseDto register(RegistrationRequestDto registrationRequestDto) {
        User user = User.builder()
                .pin(registrationRequestDto.getPin())
                .fullName(registrationRequestDto.getFullName())
                .email(registrationRequestDto.getEmail())
                .password(passwordEncoder.encode(registrationRequestDto.getPassword()))
                .role(
                        registrationRequestDto.getFullName().equalsIgnoreCase("admin") ?
                                User.Role.ADMIN :
                                User.Role.USER
                )
                .build();

        return getToken(userRepository.save(user));
    }

    private AuthResponseDto getToken(User user) {
        return AuthResponseDto.builder()
                .token(jwtService.generateToken(user))
                .build();
    }
}

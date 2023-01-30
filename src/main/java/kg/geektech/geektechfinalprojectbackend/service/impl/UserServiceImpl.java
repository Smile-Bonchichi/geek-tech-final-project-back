package kg.geektech.geektechfinalprojectbackend.service.impl;

import kg.geektech.geektechfinalprojectbackend.dto.user.request.UpdateUserDto;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import kg.geektech.geektechfinalprojectbackend.mapper.UserMapper;
import kg.geektech.geektechfinalprojectbackend.repository.UserRepository;
import kg.geektech.geektechfinalprojectbackend.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void confirm(User user) {
        userRepository.save(user.setEnabled(true));
    }

    @Override
    public UpdateUserDto updateUserInfo(UpdateUserDto updateUserDto, User user) {
        return UserMapper.INSTANCE.userToUpdateUserDto(
                userRepository.save(
                        user
                                .setPin(updateUserDto.getPin() != null ? updateUserDto.getPin() : user.getPin())
                                .setEmail(updateUserDto.getEmail() != null ? updateUserDto.getEmail() : user.getEmail())
                                .setFullName(updateUserDto.getFullName() != null ? updateUserDto.getFullName() : user.getFullName())
                                .setPhoneNumber(updateUserDto.getPhoneNumber() != null ? updateUserDto.getPhoneNumber() : user.getPhoneNumber())
                                .setPassword(updateUserDto.getPassword() != null ? passwordEncoder.encode(updateUserDto.getPassword()) : user.getPassword())
                )
        );
    }
}

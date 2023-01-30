package kg.geektech.geektechfinalprojectbackend.service.impl;

import kg.geektech.geektechfinalprojectbackend.dto.card.request.AddCardDto;
import kg.geektech.geektechfinalprojectbackend.dto.card.response.CardDto;
import kg.geektech.geektechfinalprojectbackend.dto.user.UpdateUserDto;
import kg.geektech.geektechfinalprojectbackend.dto.user.response.UserDto;
import kg.geektech.geektechfinalprojectbackend.entity.card.UserCard;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import kg.geektech.geektechfinalprojectbackend.mapper.UserMapper;
import kg.geektech.geektechfinalprojectbackend.repository.UserCardRepository;
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
    final UserCardRepository userCardRepository;
    final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserCardRepository userCardRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userCardRepository = userCardRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void confirm(User user) {
        userRepository.save(user.setEnabled(true));
    }

    @Override
    public CardDto putCard(AddCardDto addCardDto, User user) {
        return UserMapper.INSTANCE.userCardToCardDto(
                userCardRepository.save(
                        UserCard.builder()
                                .cardNumber(addCardDto.getCardNumber())
                                .user(user)
                                .build()
                )
        );
    }

    @Override
    public UserDto getUserInfo(User user) {
        return UserMapper.INSTANCE.userToUserDto(user);
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

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}

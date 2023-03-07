package kg.geektech.dostavkakgbackend.service.impl;

import kg.geektech.dostavkakgbackend.dto.card.request.AddCardDto;
import kg.geektech.dostavkakgbackend.dto.card.response.CardDto;
import kg.geektech.dostavkakgbackend.dto.user.UpdateUserDto;
import kg.geektech.dostavkakgbackend.dto.user.response.UserDto;
import kg.geektech.dostavkakgbackend.entity.card.UserCard;
import kg.geektech.dostavkakgbackend.entity.user.User;
import kg.geektech.dostavkakgbackend.exception.common.NotFoundException;
import kg.geektech.dostavkakgbackend.mapper.ImageMapper;
import kg.geektech.dostavkakgbackend.mapper.UserMapper;
import kg.geektech.dostavkakgbackend.repository.UserCardRepository;
import kg.geektech.dostavkakgbackend.repository.UserRepository;
import kg.geektech.dostavkakgbackend.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final UserCardRepository userCardRepository;
    final PasswordEncoder passwordEncoder;

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
        return UserDto.builder()
                .id(user.getId())
                .pin(user.getPin())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .imageDto(ImageMapper.INSTANCE.imageToImageResponseDto(user.getImage()))
                .build();
    }

    @Override
    public UpdateUserDto updateUserInfo(UpdateUserDto updateUserDto, User user) {
        return UserMapper.INSTANCE.userToUpdateUserDto(
                userRepository.save(
                        user
                                .setPin(
                                        updateUserDto.getPin() != null ?
                                                updateUserDto.getPin() :
                                                user.getPin()
                                )
                                .setEmail(
                                        updateUserDto.getEmail() != null ?
                                                updateUserDto.getEmail() :
                                                user.getEmail()
                                )
                                .setFullName(
                                        updateUserDto.getFullName() != null ?
                                                updateUserDto.getFullName() :
                                                user.getFullName()
                                )
                                .setPhoneNumber(
                                        updateUserDto.getPhoneNumber() != null ?
                                                updateUserDto.getPhoneNumber() :
                                                user.getPhoneNumber()
                                )
                                .setPassword(
                                        updateUserDto.getPassword() != null ?
                                                passwordEncoder.encode(updateUserDto.getPassword()) :
                                                user.getPassword()
                                )
                )
        );
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Такого пользователя нет"));
    }
}

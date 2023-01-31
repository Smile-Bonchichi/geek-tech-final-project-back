package kg.geektech.dostavkakgbackend.service;

import kg.geektech.dostavkakgbackend.dto.card.request.AddCardDto;
import kg.geektech.dostavkakgbackend.dto.card.response.CardDto;
import kg.geektech.dostavkakgbackend.dto.user.UpdateUserDto;
import kg.geektech.dostavkakgbackend.dto.user.response.UserDto;
import kg.geektech.dostavkakgbackend.entity.user.User;

public interface UserService {
    void confirm(User user);

    CardDto putCard(AddCardDto addCardDto, User user);

    UserDto getUserInfo(User user);

    UpdateUserDto updateUserInfo(UpdateUserDto updateUserDto, User user);

    void deleteUser(User user);
}

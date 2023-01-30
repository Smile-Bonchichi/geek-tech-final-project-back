package kg.geektech.geektechfinalprojectbackend.service;

import kg.geektech.geektechfinalprojectbackend.dto.card.request.AddCardDto;
import kg.geektech.geektechfinalprojectbackend.dto.card.response.CardDto;
import kg.geektech.geektechfinalprojectbackend.dto.user.UpdateUserDto;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;

public interface UserService {
    void confirm(User user);

    CardDto putCard(AddCardDto addCardDto, User user);

    UpdateUserDto updateUserInfo(UpdateUserDto updateUserDto, User user);

    void deleteUser(User user);
}

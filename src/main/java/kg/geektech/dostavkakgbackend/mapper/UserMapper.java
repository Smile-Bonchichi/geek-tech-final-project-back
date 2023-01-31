package kg.geektech.dostavkakgbackend.mapper;

import kg.geektech.dostavkakgbackend.dto.card.response.CardDto;
import kg.geektech.dostavkakgbackend.dto.user.UpdateUserDto;
import kg.geektech.dostavkakgbackend.dto.user.response.UserDto;
import kg.geektech.dostavkakgbackend.entity.card.UserCard;
import kg.geektech.dostavkakgbackend.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UpdateUserDto userToUpdateUserDto(User user);

    CardDto userCardToCardDto(UserCard userCard);

    UserDto userToUserDto(User user);
}

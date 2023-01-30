package kg.geektech.geektechfinalprojectbackend.mapper;

import kg.geektech.geektechfinalprojectbackend.dto.card.response.CardDto;
import kg.geektech.geektechfinalprojectbackend.dto.user.UpdateUserDto;
import kg.geektech.geektechfinalprojectbackend.entity.card.UserCard;
import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UpdateUserDto userToUpdateUserDto(User user);

    CardDto userCardToCardDto(UserCard userCard);
}

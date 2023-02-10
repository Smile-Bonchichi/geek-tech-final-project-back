package kg.geektech.dostavkakgbackend.mapper;

import kg.geektech.dostavkakgbackend.dto.card.response.CardDto;
import kg.geektech.dostavkakgbackend.dto.user.UpdateUserDto;
import kg.geektech.dostavkakgbackend.entity.card.UserCard;
import kg.geektech.dostavkakgbackend.entity.user.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UpdateUserDto userToUpdateUserDto(User user);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    @Mappings({
            @Mapping(target = "email", source = "user.email"),
            @Mapping(target = "fullName", source = "user.fullName")
    })
    CardDto userCardToCardDto(UserCard userCard);
}

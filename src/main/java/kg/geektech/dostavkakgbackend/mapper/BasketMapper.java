package kg.geektech.dostavkakgbackend.mapper;

import kg.geektech.dostavkakgbackend.dto.basket.BasketDto;
import kg.geektech.dostavkakgbackend.entity.basket.Basket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BasketMapper {
    BasketMapper INSTANCE = Mappers.getMapper(BasketMapper.class);

    BasketDto basketToBasketDto(Basket basket);

    List<BasketDto> basketsToBasketDtos(List<Basket> baskets);
}

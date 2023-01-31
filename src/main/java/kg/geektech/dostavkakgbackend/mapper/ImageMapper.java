package kg.geektech.dostavkakgbackend.mapper;

import kg.geektech.dostavkakgbackend.dto.image.ImageDto;
import kg.geektech.dostavkakgbackend.entity.image.Image;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageDto imageToImageResponseDto(Image image);

    List<ImageDto> imagesToImageResponseDtos(List<Image> images);
}

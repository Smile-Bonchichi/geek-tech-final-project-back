package kg.geektech.geektechfinalprojectbackend.mapper;

import kg.geektech.geektechfinalprojectbackend.dto.image.response.ImageDto;
import kg.geektech.geektechfinalprojectbackend.entity.image.Image;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageDto imageToImageResponseDto(Image image);

    List<ImageDto> imagesToImageResponseDtos(List<Image> images);
}

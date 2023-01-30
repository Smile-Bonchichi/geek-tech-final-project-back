package kg.geektech.geektechfinalprojectbackend.dto.image.response;

import kg.geektech.geektechfinalprojectbackend.dto.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageResponseDto extends BaseResponse {
    String url;
}

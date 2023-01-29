package kg.geektech.geektechfinalprojectbackend.entity.image;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kg.geektech.geektechfinalprojectbackend.entity.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Builder
@Table(name = "images")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Image extends BaseEntity {
    @NotNull
    @Column(name = "url", unique = true)
    String url;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    ImageType type;

    enum ImageType {
        AVATAR,
        IMAGE,
        ;
    }
}

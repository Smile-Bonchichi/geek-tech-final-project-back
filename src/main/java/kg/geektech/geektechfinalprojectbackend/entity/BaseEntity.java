package kg.geektech.geektechfinalprojectbackend.entity;

import jakarta.persistence.*;
import kg.geektech.geektechfinalprojectbackend.util.TimeHelperUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = TimeHelperUtil.DATE_TIME_FORMAT)
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = TimeHelperUtil.DATE_TIME_FORMAT)
    LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

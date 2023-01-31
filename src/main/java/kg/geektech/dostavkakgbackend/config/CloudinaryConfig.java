package kg.geektech.dostavkakgbackend.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CloudinaryConfig {
    @Value("${custom.cloudinary.name}")
    String name;
    @Value("${custom.cloudinary.api-key}")
    String apiKey;
    @Value("${custom.cloudinary.api-secret}")
    String apiSecret;

    @Bean
    public Cloudinary getCloudinaryBean() {
        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", name,
                        "api_key", apiKey,
                        "api_secret", apiSecret
                )
        );
    }
}

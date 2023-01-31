package kg.geektech.dostavkakgbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SwaggerConfig {
    @Value("${custom.project.name}")
    String projectName;
    final String VERSION = "1.0.0";

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group(VERSION)
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(apiEndPointsInformation())
                .servers(
                        List.of(
                                new Server()
                                        .url("http://localhost:8080/api")
                                        .description("Local service")
                        )
                );
    }

    private Info apiEndPointsInformation() {
        return new Info()
                .title("GeekTech final project")
                .description(projectName)
                .contact(
                        new Contact()
                                .name("Ulanov Nurdin")
                                .url("https://www.linkedin.com/in/smile-bonchichi/")
                                .email("ulanovnurdin@gmail.com")
                )
                .license(
                        new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")
                )
                .version(VERSION);
    }
}

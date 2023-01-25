package kg.geektech.geektechfinalprojectbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("1.0.0")
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
                                        .description("Dev service")
                        )
                );
    }

    private Info apiEndPointsInformation() {
        return new Info()
                .title("GeekTech final project")
                .description("Онлайн доставка")
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
                .version("1.0.0");
    }
}

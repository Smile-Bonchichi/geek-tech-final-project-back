package kg.geektech.dostavkakgbackend.config;

import kg.geektech.dostavkakgbackend.config.security.JwtAuthenticationFilter;
import kg.geektech.dostavkakgbackend.exception.auth.AuthenticationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SecurityConfig {
    @Value("${custom.cors.domain}")
    String corsDomain;
    final String[] WHITE_LIST_ENDPOINT = {
            //Auth
            "/user/auth/**",
            //Swagger
            "/swagger",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };
    final JwtAuthenticationFilter jwtAuthFilter;
    final AuthenticationProvider authenticationProvider;

    @Bean
    public CorsFilter corsFilter() {
        var config = new CorsConfiguration();
        config.addAllowedOrigin(corsDomain);
        config.addAllowedMethod(CorsConfiguration.ALL);
        config.setMaxAge(3600L);
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.setAllowCredentials(true);

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        try {
            return http
                    .cors().and().csrf().disable()

                    .authorizeHttpRequests()
                    .requestMatchers(WHITE_LIST_ENDPOINT).permitAll()

                    .anyRequest().authenticated()

                    .and()

                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                    .and()

                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage());
        }
    }
}

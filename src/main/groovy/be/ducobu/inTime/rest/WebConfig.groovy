package be.ducobu.inTime.rest

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebConfig implements WebMvcConfigurer {

    @Override
    void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
    }
    // CORS enabled: https://www.baeldung.com/spring-cors
}

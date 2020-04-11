package be.ducobu.inTime.config

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class ModelMapperConfig {

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper()
    }
}

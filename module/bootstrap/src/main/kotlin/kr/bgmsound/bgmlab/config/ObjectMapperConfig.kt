package kr.bgmsound.bgmlab.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class ObjectMapperConfig {

    @Bean
    fun objectMapper(builder: Jackson2ObjectMapperBuilder): ObjectMapper {
        return JsonMapper
            .builder()
            .build()
    }
}
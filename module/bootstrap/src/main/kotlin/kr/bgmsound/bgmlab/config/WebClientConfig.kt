package kr.bgmsound.bgmlab.config

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class WebClientConfig {

    @Bean
    fun webClient(): WebClient = WebClient.builder().build()
}
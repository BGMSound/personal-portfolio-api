package kr.bgmsound.bgmlab

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@EnableJpaAuditing
@EnableAspectJAutoProxy
@ConfigurationPropertiesScan
@SpringBootApplication
class BgmLabApplication

fun main() {
    runApplication<BgmLabApplication>()
}
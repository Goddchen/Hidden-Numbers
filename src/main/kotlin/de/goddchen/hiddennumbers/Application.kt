package de.goddchen.hiddennumbers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@SpringBootApplication
class Application {

    @Bean
    fun jackson2ObjectMapperBuilder(): Jackson2ObjectMapperBuilder = Jackson2ObjectMapperBuilder()
        .indentOutput(true)

}

fun main() {
    runApplication<Application>()
}
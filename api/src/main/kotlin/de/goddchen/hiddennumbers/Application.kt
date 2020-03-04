package de.goddchen.hiddennumbers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class Application {
    @Bean
    fun jackson2ObjectMapperBuilder(): Jackson2ObjectMapperBuilder = Jackson2ObjectMapperBuilder()
        .indentOutput(true)
}

@Configuration
class WebConfiguration : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        super.addCorsMappings(registry)
        registry.addMapping("/**").allowedOrigins("*")
    }
}

fun main() {
    runApplication<Application>()
}
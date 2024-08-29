package com.flip.urlshorterner.app

import com.flip.urlshorterner.common.validation.DefaultStaticValidator
import com.flip.urlshorterner.shortener.properties.ShortenerProperties
import io.mongock.runner.springboot.EnableMongock
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@OpenAPIDefinition(
    info = Info(
        title = "Url shortener API",
        description = "A simple url shortener. Don't use it in prod.",
        version = "0.0.1"
    )
)
@SpringBootApplication(
    scanBasePackages = [
        "com.flip.urlshorterner.dispatcher.*",
        "com.flip.urlshorterner.shortener.*",
        "com.flip.urlshorterner.exception"
    ]
)
@EnableMongock
@EnableMongoRepositories(
    basePackages = ["com.flip.urlshorterner.shortener.persistence"]
)
@EnableConfigurationProperties(ShortenerProperties::class)
class UrlShortenerApp {

    @Bean
    fun staticValidator() = DefaultStaticValidator()


}

fun main(args: Array<String>) {
    runApplication<UrlShortenerApp>(*args)
}
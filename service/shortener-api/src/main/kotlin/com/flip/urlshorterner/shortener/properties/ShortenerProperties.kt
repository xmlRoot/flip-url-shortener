package com.flip.urlshorterner.shortener.properties

import org.jetbrains.annotations.NotNull
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

@ConfigurationProperties(prefix = "shortener")
@Validated
data class ShortenerProperties(
    @get:NotNull
    val urlPrefix : String
)

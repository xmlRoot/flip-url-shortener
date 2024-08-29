package com.flip.urlshorterner.shortener.service

import com.flip.urlshorterner.common.validation.StaticValidator
import com.flip.urlshorterner.shortener.model.document.ShortUrl
import com.flip.urlshorterner.shortener.model.dto.ShortDto
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class ShortenerServiceDelegateImpl(
    @Qualifier(SHORTENER_SERVICE_ID) private val shortenService : ShortenerService,
    private val validator : StaticValidator
) : ShortenerServiceDelegate, ShortenerService by shortenService{

    override fun shorten(url: ShortDto): ShortUrl {
        validator.validate(url)
        return shorten(url.url)
    }
}
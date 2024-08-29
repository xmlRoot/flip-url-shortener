package com.flip.urlshorterner.shortener.api.view

import com.flip.urlshorterner.shortener.api.view.model.ShortUrlView
import com.flip.urlshorterner.shortener.model.document.ShortUrl
import com.flip.urlshorterner.shortener.properties.ShortenerProperties
import org.springframework.stereotype.Component

@Component
class ShortenerApiViewFacadeImpl(
    private val props : ShortenerProperties
) : ShortenerApiViewFacade {

    override fun toView(url: ShortUrl): ShortUrlView =
        ShortUrlView(
            uuid = url.uuid,
            shortUrl = "${props.urlPrefix}/${url.shortenedUrl}"
        )

}
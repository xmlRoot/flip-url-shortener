package com.flip.urlshorterner.dispatcher.api

import com.flip.urlshorterner.shortener.service.ShortenerServiceDelegate
import org.apache.tomcat.websocket.Constants.FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class DispatcherApi(
    private val shortenerService : ShortenerServiceDelegate
) {

    companion object Url {
        const val DISPATCH = "/s/{shortUrl}"
    }

    @GetMapping(DISPATCH)
    fun dispatch(
        @PathVariable shortUrl: String
    ) : ResponseEntity<Any> = shortenerService.get(shortUrl).let { dbShortUrl ->
        ResponseEntity.status(FOUND).location(URI.create(dbShortUrl.targetUri)).build()
    }

}
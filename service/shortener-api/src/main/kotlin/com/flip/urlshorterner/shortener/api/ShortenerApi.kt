package com.flip.urlshorterner.shortener.api

import com.flip.urlshorterner.shortener.api.view.ShortenerApiViewFacade
import com.flip.urlshorterner.shortener.model.dto.ShortDto
import com.flip.urlshorterner.shortener.service.ShortenerServiceDelegate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ShortenerApi(
    private val shortener : ShortenerServiceDelegate,
    private val viewFacade : ShortenerApiViewFacade
) {

    companion object Url {
        const val SHORTEN_URL = "/shorten/url"
    }

    @PostMapping(SHORTEN_URL)
    fun shorten(
        @RequestBody url: ShortDto
    ) = shortener.shorten(url).let { viewFacade.toView(it) }

}
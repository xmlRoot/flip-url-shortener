package com.flip.urlshorterner.shortener.api.view

import com.flip.urlshorterner.shortener.api.view.model.ShortUrlView
import com.flip.urlshorterner.shortener.model.document.ShortUrl

interface ShortenerApiViewFacade {

    fun toView(url : ShortUrl) : ShortUrlView

}
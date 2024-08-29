package com.flip.urlshorterner.shortener.service

import com.flip.urlshorterner.shortener.model.document.ShortUrl

interface ShortenerService {

    fun shorten(uri: String): ShortUrl

    fun get(shortUrl : String) : ShortUrl

}
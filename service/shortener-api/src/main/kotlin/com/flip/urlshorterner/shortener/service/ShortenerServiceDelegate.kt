package com.flip.urlshorterner.shortener.service

import com.flip.urlshorterner.shortener.model.document.ShortUrl
import com.flip.urlshorterner.shortener.model.dto.ShortDto

interface ShortenerServiceDelegate : ShortenerService {

    fun shorten(url : ShortDto) : ShortUrl

}
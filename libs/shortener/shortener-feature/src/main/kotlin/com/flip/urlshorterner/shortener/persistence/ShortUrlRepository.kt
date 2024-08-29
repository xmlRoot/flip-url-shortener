package com.flip.urlshorterner.shortener.persistence

import com.flip.urlshorterner.shortener.model.document.ShortUrl
import org.springframework.data.mongodb.repository.MongoRepository

interface ShortUrlRepository : MongoRepository<ShortUrl, String> {

    fun findByUuid(uuid: String): ShortUrl?

    fun findByShortenedUrl(shortUrl: String): ShortUrl?

}
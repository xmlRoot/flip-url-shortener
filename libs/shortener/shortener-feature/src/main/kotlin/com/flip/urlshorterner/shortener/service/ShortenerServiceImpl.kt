package com.flip.urlshorterner.shortener.service

import com.flip.urlshorterner.common.exception.model.ShortenerException
import com.flip.urlshorterner.common.log.LoggerDelegate
import com.flip.urlshorterner.common.util.DocUtils.uuid
import com.flip.urlshorterner.shortener.exception.ShortenerError.MALFORMED_URI
import com.flip.urlshorterner.shortener.exception.ShortenerError.MALFORMED_URL
import com.flip.urlshorterner.shortener.exception.ShortenerError.SHORT_URL_NOT_FOUND
import com.flip.urlshorterner.shortener.model.document.ShortUrl
import com.flip.urlshorterner.shortener.persistence.ShortUrlRepository
import com.flip.urlshorterner.shortener.service.usecase.ShortenUrlUseCase
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import java.net.MalformedURLException
import java.net.URI
import java.net.URISyntaxException

const val SHORTENER_SERVICE_ID = "shortenerService"

@Service(SHORTENER_SERVICE_ID)
class ShortenerServiceImpl(
    private val createShortVersion : ShortenUrlUseCase,
    private val shortUrls : ShortUrlRepository
) : ShortenerService {

    private val log by LoggerDelegate()

    override fun shorten(uri: String): ShortUrl {
        validate(uri)
        // validate this url is actually an url
        // pick up the uri segment
        return shortUrls.save(ShortUrl(
            shortenedUrl = createShortVersion(uri).also {
                log.debug("Shortening {} to {}", uri, it)
            },
            targetUri = uri,
            id = ObjectId().toString(),
            uuid = uuid()
        ))
    }

    // very quick and naive validation
    private fun validate(uri: String) {
        try {
            URI(uri).toURL()
        }catch (ex : MalformedURLException) {
            throw ShortenerException(type = MALFORMED_URL, msg = ex.message ?: "-", value = uri )
        } catch (ex : URISyntaxException) {
            throw ShortenerException(type = MALFORMED_URI, msg = ex.reason ?: "-", value = uri)
        }
    }

    override fun get(shortUrl: String): ShortUrl =
        shortUrls.findByShortenedUrl(shortUrl) ?: throw ShortenerException(
            type = SHORT_URL_NOT_FOUND,
            msg = "No expanded url found for provided short url"
        )

}
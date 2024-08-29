package com.flip.urlshorterner.shortener.service

import com.flip.urlshorterner.shortener.service.usecase.ShortenUrlUseCase.Companion.SHORT_URL_SIZE
import com.flip.urlshorterner.shortener.service.usecase.ShortenUrlUseCaseImpl
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.haveLength

class TestShortenerService : FreeSpec({

    val service = ShortenerServiceImpl(
        createShortVersion = ShortenUrlUseCaseImpl(),
        shortUrls = ShortUrlRepositoryMockImpl
    )

    "testing url shortener" - {
        "with conforming url" - {
            val url = "https://where.are.com/my/socks?green=true"

            service.shorten(url).let { shortenUrl ->
                shortenUrl.targetUri shouldBe url
                shortenUrl.shortenedUrl should haveLength(SHORT_URL_SIZE)
                ShortUrlRepositoryMockImpl.db[shortenUrl.id] shouldNotBeNull {}
            }
        }
    }

})
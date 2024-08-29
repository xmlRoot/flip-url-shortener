package com.flip.urlshorterner.shortener.service.usecase

interface ShortenUrlUseCase {

    companion object {
        const val SHORT_URL_SIZE = 15
    }

    operator fun invoke(url: String): String
}
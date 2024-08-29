package com.flip.urlshorterner.shortener.exception

import com.flip.urlshorterner.common.exception.model.ErrorType

enum class ShortenerError : ErrorType {
    SHORT_URL_NOT_FOUND,
    MALFORMED_URL,
    MALFORMED_URI
}
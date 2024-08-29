package com.flip.urlshorterner.common.validation

import com.flip.urlshorterner.common.exception.model.ShortenerException

interface StaticValidator {

    @Throws(ShortenerException::class)
    fun validate(value: Any, msgPrefix : String = "", fieldPrefix : String = "")
}
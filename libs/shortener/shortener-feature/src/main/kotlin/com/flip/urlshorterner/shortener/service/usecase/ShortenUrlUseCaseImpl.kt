package com.flip.urlshorterner.shortener.service.usecase

import com.flip.urlshorterner.common.util.DocUtils.uuid
import com.flip.urlshorterner.shortener.service.usecase.ShortenUrlUseCase.Companion.SHORT_URL_SIZE
import org.springframework.stereotype.Component

@Component
class ShortenUrlUseCaseImpl : ShortenUrlUseCase {

    override fun invoke(url: String): String = uuid(SHORT_URL_SIZE)

}
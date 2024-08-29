package com.flip.urlshorterner.shortener.model.document

import com.flip.urlshorterner.common.model.document.DocumentEntity
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document

@Document("short_url")
@TypeAlias("short_url")
data class ShortUrl(
    override val id: String,
    override val uuid: String,

    val targetUri : String,
    val shortenedUrl : String,
) : DocumentEntity

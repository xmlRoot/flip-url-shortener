package com.flip.urlshorterner.common.model.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Field

interface DocumentEntity{
    @get:Id
    val id: String
    @get:Field(name = "_uuid")
    val uuid: String
}
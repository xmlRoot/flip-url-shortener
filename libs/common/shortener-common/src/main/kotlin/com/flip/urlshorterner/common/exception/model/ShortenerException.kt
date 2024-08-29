package com.flip.urlshorterner.common.exception.model

open class ShortenerException(
    val container : ErrorContainer
) : RuntimeException(container.msg) {

    constructor(type : ErrorType, msg : String = "-", field : String = "-", value : Any? = "-") : this(ErrorContainer(
        type = type, msg = msg, value = value, field = field
    ))

}
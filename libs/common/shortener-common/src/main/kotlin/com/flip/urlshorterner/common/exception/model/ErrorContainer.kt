package com.flip.urlshorterner.common.exception.model

const val NO_VALUE = "-"

data class ErrorContainer(
    val type : ErrorType,
    val field : String = NO_VALUE,
    val msg : String = NO_VALUE,
    val value : Any? = NO_VALUE
)

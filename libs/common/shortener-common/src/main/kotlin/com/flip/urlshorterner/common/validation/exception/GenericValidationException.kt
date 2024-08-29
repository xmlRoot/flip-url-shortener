package com.flip.urlshorterner.common.validation.exception

import com.flip.urlshorterner.common.exception.model.ErrorType
import com.flip.urlshorterner.common.exception.model.ShortenerException
import com.flip.urlshorterner.common.validation.exception.ValidationError.INVALID_DATA
import jakarta.validation.ConstraintViolation

enum class ValidationError : ErrorType {
    INVALID_DATA
}

class GenericValidationException(
    violation : ConstraintViolation<Any>,
    msgPrefix : String,
    fieldPrefix : String = ""
) : ShortenerException(
    field = "$fieldPrefix${violation.propertyPath}" ,
    value = violation.invalidValue,
    type = INVALID_DATA,
    msg = "$msgPrefix${violation.message}"
)
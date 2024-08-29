package com.flip.urlshorterner.common.validation

import com.flip.urlshorterner.common.log.LoggerDelegate
import com.flip.urlshorterner.common.validation.exception.GenericValidationException
import jakarta.validation.ConstraintViolation
import jakarta.validation.Validation
import jakarta.validation.Validator

class DefaultStaticValidator(
    private val validator: Validator = Validation.buildDefaultValidatorFactory().validator
) : StaticValidator {

    private val log by LoggerDelegate()

    override fun validate(value: Any, msgPrefix: String, fieldPrefix: String) {
        val violations = validator.validate(value)
        if (violations.isNullOrEmpty()) {
            return
        }
        violations.forEach { violation: ConstraintViolation<Any> ->
            log.error(
                "property {} failed with constraint {}",
                violation.propertyPath,
                "$msgPrefix${violation.message}"
            )
            throw GenericValidationException(violation, msgPrefix, fieldPrefix)
        }
    }
}
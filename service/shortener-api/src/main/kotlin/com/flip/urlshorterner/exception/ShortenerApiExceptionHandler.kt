package com.flip.urlshorterner.exception

import com.flip.urlshorterner.common.exception.model.ErrorContainer
import com.flip.urlshorterner.common.exception.model.ErrorType
import com.flip.urlshorterner.common.exception.model.ShortenerException
import com.flip.urlshorterner.common.log.LoggerDelegate
import jakarta.validation.ConstraintViolationException
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.resource.NoResourceFoundException


enum class ApiError : ErrorType {
    BAD_REQUEST,
    INVALID_DATA,
    SERVER_ERROR,
    METHOD_NOT_SUPPORTED,
    URL_NOT_FOUND
}

@ControllerAdvice
@Order(3)
class ShortenerApiExceptionHandler {

    companion object {
        val GENERIC_BAD_REQUEST = ErrorContainer(type = ApiError.BAD_REQUEST)
    }

    private val log by LoggerDelegate()

    @ExceptionHandler(ShortenerException::class)
    fun handleCommonException(e: ShortenerException): ResponseEntity<*>? {
        log.error(
            "Field: {} Value: {} of type {} with msg : {}",
            e.container.field, e.container.value, e.container.type, e.container.msg
        )
        log.debug("", e)
        return returnStatus(BAD_REQUEST, e)
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleAllRuntimeException(e: RuntimeException): ResponseEntity<*> {
        log.error(e.message)
        log.error("", e)
        return returnStatus(
            HttpStatus.BAD_REQUEST,
            GENERIC_BAD_REQUEST.copy(msg = "Please check server logs for more information.")
        )
    }

    @ExceptionHandler(Throwable::class)
    fun handleAllException(e: Throwable): ErrorContainer {
        log.debug(
            "Handling error of type {} with generic Throwable handler.",
            e.javaClass.name
        )
        log.error(e.message)
        log.error("", e)
        return serverError("The service is unavailable. Please try again shortly.")
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleMethodNotSupported(e: HttpRequestMethodNotSupportedException): ErrorContainer {
        log.error(e.message)
        log.error("", e)
        val errMsg = if (!e.supportedHttpMethods.isNullOrEmpty())
            "${e.message}. Supported methods are: ${e.supportedHttpMethods}"
        else e.message
        return methodNotSupported(e.method, errMsg!!)
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun catchResourceNotFound(ex: NoResourceFoundException): ResponseEntity<*> {
        log.error(ex.message)
        log.error("", ex)
        return status<ErrorContainer, ShortenerException>(
            HttpStatus.NOT_FOUND,
            urlNotFound(ex.resourcePath, ex.message ?: "")
        )
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun catchConstraintViolations(ex: ConstraintViolationException) =
        getFirstViolation(ex)?.let {
            log.error(ex.message)
            status<ErrorContainer, ShortenerException>(
                HttpStatus.BAD_REQUEST, ErrorContainer(
                    msg = it.message,
                    field = it.propertyPath.toString(),
                    value = it.invalidValue,
                    type = ApiError.INVALID_DATA
                )
            )
        } ?: handleAllRuntimeException(ex)


    fun serverError(msg: String): ErrorContainer =
        ErrorContainer(
            value = HttpStatus.INTERNAL_SERVER_ERROR.toString(),
            type = ApiError.SERVER_ERROR,
            msg = msg
        )

    fun methodNotSupported(method: String, errMsg: String): ErrorContainer =
        ErrorContainer(
            field = "method",
            value = method,
            type = ApiError.METHOD_NOT_SUPPORTED,
            msg = errMsg)

    fun urlNotFound(url: String, msg: String): ErrorContainer =
        ErrorContainer(value = url, field = "url", type = ApiError.URL_NOT_FOUND, msg = msg)

    private fun getFirstViolation(ex: ConstraintViolationException) = ex.constraintViolations?.firstOrNull()

    private fun <T, E : ShortenerException> status(status: HttpStatus, payload: T): ResponseEntity<out T> =
        ResponseEntity.status(status).body(payload)

    private fun <E : ShortenerException> returnStatus(status: HttpStatus, e: E): ResponseEntity<*> {
        return returnStatus(status, e.container)
    }

    private fun returnStatus(status: HttpStatus, error: ErrorContainer): ResponseEntity<ErrorContainer> =
        ResponseEntity.status(status).body(error.copy(value = error.value?.toString()))

}
package com.baeolian.idealisto.domain.model

sealed class DataException(
    cause: Throwable?,
    message: String?,
) : Throwable(
    cause = cause,
    message = message,
) {
    data class ApiCallException(
        val code: Int,
        val apiLocalizedMessage: String?,
        val apiError: Throwable?
    ) : DataException(cause = apiError, message = apiError?.message)

    data class MappingException(val mappingError: Throwable?, val data: Any) :
        DataException(cause = mappingError, message = mappingError?.message)
}

package com.baeolian.idealisto.data.service

import com.baeolian.idealisto.domain.model.DataException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response: T = apiCall()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(
                DataException.ApiCallException(
                    code = -1,
                    apiLocalizedMessage = null,
                    apiError = e
                )
            )
        }
    }
}

suspend fun <T, R> safeApiCall(apiCall: suspend () -> T, toDomain: (T) -> R): Result<R> {
    val apiResult = safeApiCall(apiCall)
    return try {
        apiResult.map(toDomain)
    } catch (e: Exception) {
        Result.failure(DataException.MappingException(mappingError = e, data = apiResult))
    }
}

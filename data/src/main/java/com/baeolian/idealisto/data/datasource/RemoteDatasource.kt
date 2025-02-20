package com.baeolian.idealisto.data.datasource

import com.baeolian.idealisto.data.service.ApiService
import com.baeolian.idealisto.data.service.safeApiCall
import com.baeolian.idealisto.domain.model.PropertyDetailsModel
import com.baeolian.idealisto.domain.model.PropertyModel
import javax.inject.Inject

class RemoteDatasource @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun getPropertyList(): Result<List<PropertyModel>> {
        return safeApiCall(
            apiCall = { apiService.getPropertyList() },
            toDomain = { list -> list.map { it.toDomain() } }
        )
    }

    suspend fun getPropertyDetails(): Result<PropertyDetailsModel> {
        return safeApiCall(
            apiCall = { apiService.getPropertyDetails() },
            toDomain = { it.toDomain() }
        )
    }
}

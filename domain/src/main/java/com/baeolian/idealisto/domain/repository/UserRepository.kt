package com.baeolian.idealisto.domain.repository

import com.baeolian.idealisto.domain.model.PropertyDetailsModel
import com.baeolian.idealisto.domain.model.PropertyModel
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface UserRepository {

    suspend fun getPropertyList(): Result<List<PropertyModel>>

    fun getFavorites(): Flow<Map<String, Date>>

    fun addFavorite(propertyCode: String, date: Date)

    fun deleteFavorite(propertyCode: String)

    suspend fun getPropertyDetails(): Result<PropertyDetailsModel>
}

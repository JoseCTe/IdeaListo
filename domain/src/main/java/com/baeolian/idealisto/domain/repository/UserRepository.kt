package com.baeolian.idealisto.domain.repository

import com.baeolian.idealisto.domain.model.PropertyDetailsModel
import com.baeolian.idealisto.domain.model.PropertyModel

interface UserRepository {

    suspend fun getPropertyList(): Result<List<PropertyModel>>

    suspend fun getPropertyDetails(): Result<PropertyDetailsModel>
}

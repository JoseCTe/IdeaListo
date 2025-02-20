package com.baeolian.idealisto.data.repository

import com.baeolian.idealisto.data.datasource.RemoteDatasource
import com.baeolian.idealisto.domain.model.PropertyDetailsModel
import com.baeolian.idealisto.domain.model.PropertyModel
import com.baeolian.idealisto.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remoteDatasource: RemoteDatasource,
) : UserRepository {

    override suspend fun getPropertyList(): Result<List<PropertyModel>> {
        return remoteDatasource.getPropertyList()
    }

    override suspend fun getPropertyDetails(): Result<PropertyDetailsModel> {
        return remoteDatasource.getPropertyDetails()
    }
}

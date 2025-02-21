package com.baeolian.idealisto.data.repository

import com.baeolian.idealisto.data.datasource.LocalDatasource
import com.baeolian.idealisto.data.datasource.RemoteDatasource
import com.baeolian.idealisto.domain.model.PropertyDetailsModel
import com.baeolian.idealisto.domain.model.PropertyModel
import com.baeolian.idealisto.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date

class UserRepositoryImpl(
    private val remoteDatasource: RemoteDatasource,
    private val localDatasource: LocalDatasource
) : UserRepository {

    override suspend fun getPropertyList(): Result<List<PropertyModel>> {
        return remoteDatasource.getPropertyList()
    }

    override fun getFavorites(): Flow<Map<String, Date>> {
        localDatasource.fetchFavorites()
        return localDatasource.favorites
    }

    override fun addFavorite(propertyCode: String, date: Date) = localDatasource.addFavorite(propertyCode, date)

    override fun deleteFavorite(propertyCode: String) = localDatasource.deleteFavorite(propertyCode)

    override suspend fun getPropertyDetails(): Result<PropertyDetailsModel> {
        return remoteDatasource.getPropertyDetails()
    }
}

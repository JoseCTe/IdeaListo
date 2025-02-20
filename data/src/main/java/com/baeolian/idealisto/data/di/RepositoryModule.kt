package com.baeolian.idealisto.data.di

import com.baeolian.idealisto.data.datasource.RemoteDatasource
import com.baeolian.idealisto.data.repository.UserRepositoryImpl
import com.baeolian.idealisto.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(remoteDatasource: RemoteDatasource): UserRepository {
        return UserRepositoryImpl(remoteDatasource = remoteDatasource)
    }
}

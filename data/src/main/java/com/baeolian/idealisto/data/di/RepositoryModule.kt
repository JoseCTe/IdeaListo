package com.baeolian.idealisto.data.di

import android.content.Context
import android.content.SharedPreferences
import android.provider.SyncStateContract
import com.baeolian.idealisto.data.datasource.LocalDatasource
import com.baeolian.idealisto.data.datasource.RemoteDatasource
import com.baeolian.idealisto.data.repository.UserRepositoryImpl
import com.baeolian.idealisto.domain.repository.UserRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        remoteDatasource: RemoteDatasource,
        localDatasource: LocalDatasource,
    ): UserRepository {
        return UserRepositoryImpl(
            remoteDatasource = remoteDatasource,
            localDatasource = localDatasource,
        )
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            SyncStateContract.Constants.ACCOUNT_NAME, Context.MODE_PRIVATE
        )
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}

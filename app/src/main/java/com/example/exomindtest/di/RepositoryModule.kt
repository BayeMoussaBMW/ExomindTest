package com.example.exomindtest.di


import com.example.exomindtest.model.Address
import com.example.exomindtest.model.Company
import com.example.exomindtest.repository.MainRepository
import com.example.exomindtest.retrofit.ExomindRetrofit
import com.example.exomindtest.retrofit.NetworkMapper
import com.example.exomindtest.room.CacheMapper
import com.example.exomindtest.room.UserItemDao
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
    fun provideMainRepository(
        userItemDao: UserItemDao,
        retrofit: ExomindRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper,
    ): MainRepository {
        return MainRepository(userItemDao, retrofit, cacheMapper, networkMapper)
    }
}















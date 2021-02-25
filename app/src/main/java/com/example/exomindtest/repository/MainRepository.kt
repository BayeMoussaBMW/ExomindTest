package com.example.exomindtest.repository

import com.example.exomindtest.model.AlbumItem
import com.example.exomindtest.model.UserItem
import com.example.exomindtest.retrofit.ExomindRetrofit
import com.example.exomindtest.retrofit.NetworkMapper
import com.example.exomindtest.retrofit.UserItemNetworkEntity
import com.example.exomindtest.room.CacheMapper
import com.example.exomindtest.room.UserItemCacheEntity
import com.example.exomindtest.room.UserItemDao
import com.example.exomindtest.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository
constructor(
    private val userItemDao: UserItemDao,
    private val blogRetrofit: ExomindRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper,
) {
    suspend fun getUserItem(): Flow<DataState<List<UserItem>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkUsers = blogRetrofit.get()
            print(networkUsers)
            val users = networkMapper.mapFromEntityList(networkUsers)
            for (user in users) {
                userItemDao.insert(cacheMapper.mapToEntity(user))
            }
            val cachedUsers = userItemDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedUsers)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getAlbum(): Flow<DataState<AlbumItem>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkAlbumItem = blogRetrofit.getAlbum()
            print(networkAlbumItem)
        }catch (e: Exception){
            print(e)
        }
    }


    suspend fun searchUser(s: String): Flow<DataState<List<UserItem>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val resultsFromNetwork = blogRetrofit.getUserSearch(s)
            print(resultsFromNetwork)
            val results = networkMapper.mapFromEntityList(resultsFromNetwork)
            for (result in results){
                userItemDao.insert(cacheMapper.mapToEntity(result))
            }
            val cachedUsers = userItemDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedUsers)))
        }catch (e: Exception){
            print(e)
        }

    }

}
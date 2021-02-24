package com.example.exomindtest.retrofit


import com.example.exomindtest.model.AlbumItem
import retrofit2.http.GET

interface ExomindRetrofit {

    @GET("users")
    suspend fun get(): List<UserItemNetworkEntity>

    @GET("users/1/albums?userId={userID}")
    suspend fun getAlbum(): AlbumItem

}


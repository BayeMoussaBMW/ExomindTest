package com.example.exomindtest.retrofit


import retrofit2.http.GET

interface ExomindRetrofit {

    @GET("users")
    suspend fun get(): List<UserItemNetworkEntity>

}


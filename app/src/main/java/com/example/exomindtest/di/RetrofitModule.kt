package com.example.exomindtest.di

import com.example.exomindtest.retrofit.ExomindRetrofit
import com.example.exomindtest.retrofit.UserItemNetworkEntity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {


    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

   /* @Singleton
    @Provides
    fun provideRetrofit(gson:  Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }*/

    @Singleton
    @Provides
    fun provideRetrofit(): ExomindRetrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ExomindRetrofit::class.java)
    }

   /* @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit.Builder): ExomindRetrofit {
        return retrofit
            .build()
            .create(ExomindRetrofit::class.java)
    }*/

}





















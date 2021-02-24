package com.example.exomindtest.di

import android.content.Context
import androidx.room.Room
import com.example.exomindtest.room.ExomindDatabase
import com.example.exomindtest.room.UserItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideExomindDb(@ApplicationContext context: Context): ExomindDatabase {
        return Room
            .databaseBuilder(
                context,
                ExomindDatabase::class.java,
                ExomindDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserItemDAO(exomindDatabase: ExomindDatabase): UserItemDao {
        return exomindDatabase.userItemDao()
    }

}
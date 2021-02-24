package com.example.exomindtest.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userItemCacheEntity: UserItemCacheEntity): Long

    @Query("SELECT * FROM useritem")
    suspend fun get(): List<UserItemCacheEntity>
}
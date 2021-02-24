package com.example.exomindtest.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [UserItemCacheEntity::class ], version = 1, exportSchema = false)
@TypeConverters(AddressTypeConverter::class, GeoTypeConverter::class, CompanyTypeConverter::class)
abstract class ExomindDatabase: RoomDatabase() {

    abstract fun userItemDao(): UserItemDao

    companion object{
        val DATABASE_NAME: String = "exomind_db"
    }
}

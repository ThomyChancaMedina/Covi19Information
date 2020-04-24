package com.example.testcovid.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [
    Latest::class,
    Location::class
], version = 1)
abstract class LocationDatabase : RoomDatabase() {
    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            LocationDatabase::class.java,
            "Location-db"
        ).build()
    }

    abstract fun LocationDao(): LocationDao

    abstract fun LatestDao(): LatestDao
}
package com.example.testcovid.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        Latest::class,
        Location::class,
        Covid19::class
    ], version = 1
)
abstract class CovidDatabase : RoomDatabase() {
    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            CovidDatabase::class.java,
            "Covid19DB"
        ).build()
    }

    abstract fun LocationDao(): LocationDao

    abstract fun LatestDao(): LatestDao

    abstract fun CovidDao(): CovidGlobalDao
}
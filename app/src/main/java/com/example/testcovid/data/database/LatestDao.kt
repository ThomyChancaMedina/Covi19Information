package com.example.testcovid.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LatestDao {
    @Query("SELECT * FROM LatestData")
    fun getAllLatest(): Latest

    @Query("SELECT COUNT(recovered) FROM LatestData")
    fun LatestCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLatest(latest: Latest)


}
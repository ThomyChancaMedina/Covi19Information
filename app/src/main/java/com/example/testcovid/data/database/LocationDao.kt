package com.example.testcovid.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocationDao {

    @Query("SELECT * FROM LocationData")
    fun getAllLocation(): List<Location>

    @Query("SELECT COUNT(id) FROM LocationData")
    fun LocationCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLocations(location: List<Location>)

}
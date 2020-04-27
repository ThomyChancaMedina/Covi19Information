package com.example.testcovid.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import retrofit2.http.GET




@Dao
interface CovidGlobalDao {
    @Query("SELECT * FROM Covid19Data")
    fun getAllDataCovid(): Covid19

    @Query("SELECT COUNT(id) FROM COVID19DATA")
    fun covid19Count():Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCovid(covid19: Covid19)

//    @GET("/stockers/login")
//    fun login(
//        @Query("email") email: String?,
//        @Query("password") password: String?,
//        callback: Callback<MyResponse?>?
//    )

}
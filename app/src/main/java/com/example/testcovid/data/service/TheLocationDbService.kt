package com.example.testcovid.data.service

import com.example.testcovid.data.database.DataCovid
import retrofit2.http.GET

interface TheLocationDbService {


    @GET("locations")
    suspend fun getLocation(): DataCovid

    @GET("latest")
    suspend fun getLatest(): DataCovid

}
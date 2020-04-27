package com.example.testcovid.data.service

import com.example.testcovid.data.database.Covid19
import com.example.testcovid.data.database.DataCovid
import retrofit2.http.GET

interface Covid19DbService {


    @GET("api/")
    suspend fun getRemoteCovid():Covid19

    @GET("locations")
    suspend fun getLocation(): DataCovid

    @GET("latest")
    suspend fun getLatest(): DataCovid

}
package com.example.testcovid.data.source


import com.example.testcovid.domain.model.Covid19Imp
import com.example.testcovid.domain.model.LatestImp
import com.example.testcovid.domain.model.LocationImp

interface RemoteDataSource {
    suspend fun getPopularLocation():List<LocationImp>
    suspend fun getDataLatest():LatestImp
    suspend fun getDataCovid19(): Covid19Imp

}
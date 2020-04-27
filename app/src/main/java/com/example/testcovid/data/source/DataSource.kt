package com.example.testcovid.data.source



import com.example.testcovid.data.database.Covid19
import com.example.testcovid.domain.model.Covid19Imp
import com.example.testcovid.domain.model.LatestImp
import com.example.testcovid.domain.model.LocationImp

interface DataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveLocation(location: List<LocationImp>)
    suspend fun getLocationsUse(): List<LocationImp>

    suspend fun isEmptyLast():Boolean
    suspend fun saveLatest(latest: LatestImp)
    suspend fun getLatest(): LatestImp

    suspend fun isEmptyCovid19(): Boolean
    suspend fun saveCovid19(covidOverview: Covid19Imp)
    suspend fun getCovid19(): Covid19Imp

}
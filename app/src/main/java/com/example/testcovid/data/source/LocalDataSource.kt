package com.example.testcovid.data.source


import com.example.testcovid.domain.model.LatestImp
import com.example.testcovid.domain.model.LocationImp

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveLocation(location: List<LocationImp>)
    suspend fun getLocationsUse(): List<LocationImp>

    suspend fun isEmptyLast():Boolean
    suspend fun saveLatest(latest: LatestImp)
    suspend fun getLatest(): LatestImp

}
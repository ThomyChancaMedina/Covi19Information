package com.example.testcovid.data.repository

import com.example.testcovid.data.source.DataSource
import com.example.testcovid.data.source.RemoteDataSource
import com.example.testcovid.domain.model.LocationImp


class LocationRepository(
    private val localDataSource: DataSource,
    private val remoteDataSource: RemoteDataSource
) {
    private val TAG=LocationRepository::class.java.simpleName

    suspend fun getLocations(): List<LocationImp> {

        if(localDataSource.isEmpty()){
            val location=remoteDataSource.getPopularLocation()

            localDataSource.saveLocation(location)

        }


        return localDataSource.getLocationsUse()
    }
}
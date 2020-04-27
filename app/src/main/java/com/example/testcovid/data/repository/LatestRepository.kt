package com.example.testcovid.data.repository

import com.example.testcovid.data.source.DataSource
import com.example.testcovid.data.source.RemoteDataSource
import com.example.testcovid.domain.model.LatestImp

class LatestRepository(
    private val localDataSource: DataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getLatest(): LatestImp {
        if(localDataSource.isEmptyLast()){
            val latest=remoteDataSource.getDataLatest()

            localDataSource.saveLatest(latest)
        }
        return localDataSource.getLatest()
    }

}
package com.example.testcovid.data.service

import com.example.testcovid.data.source.RemoteDataSource
import com.example.testcovid.data.toDomainServer
import com.example.testcovid.domain.model.LatestImp
import com.example.testcovid.domain.model.LocationImp


class TheLocationDbDataSource(val theLocationDb: TheLocationDb) : RemoteDataSource {

    override suspend fun getPopularLocation(): List<LocationImp> =
        theLocationDb.service
            .getLocation()
            .locations
            .map { it.toDomainServer()}

    override suspend fun getDataLatest(): LatestImp =
        theLocationDb.service
            .getLatest()
            .latest
            .toDomainServer()



}
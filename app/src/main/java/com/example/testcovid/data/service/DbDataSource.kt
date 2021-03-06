package com.example.testcovid.data.service

import android.util.Log
import com.example.testcovid.data.source.RemoteDataSource
import com.example.testcovid.data.toDomainServer
import com.example.testcovid.domain.model.Covid19Imp
import com.example.testcovid.domain.model.LatestImp
import com.example.testcovid.domain.model.LocationImp


class DbDataSource(val theLocationDb: TheLocationDb) : RemoteDataSource {
    private val TAG= DbDataSource::class.java.simpleName

    override suspend fun getPopularLocation(): List<LocationImp> =
        theLocationDb.service
            .getLocation()
            .locations
            .map { it.toDomainServer() }

    override suspend fun getDataLatest(): LatestImp =
        theLocationDb.service
            .getLatest()
            .latest
            .toDomainServer()

    override suspend fun getDataCovid19(): Covid19Imp =
        theLocationDb.service
            .getRemoteCovid()
            .toDomainServer()





}
package com.example.testcovid.data.database

import com.example.testcovid.data.*
import com.example.testcovid.data.source.DataSource
import com.example.testcovid.domain.model.Covid19Imp
import com.example.testcovid.domain.model.LatestImp
import com.example.testcovid.domain.model.LocationImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: CovidDatabase) : DataSource {


    private val latestDao = db.LatestDao()
    private val locationDao = db.LocationDao()
    private val covidDao = db.CovidDao()

    //
    override suspend fun isEmptyLast(): Boolean =
        withContext(Dispatchers.IO) { latestDao.LatestCount() <= 0 }


    override suspend fun saveLatest(latest: LatestImp) {
        withContext(Dispatchers.IO) { latestDao.insertLatest(latest.toRoomLatest()) }
    }

    override suspend fun getLatest(): LatestImp = withContext(Dispatchers.IO) {
        latestDao.getAllLatest().toDomainLatest()
    }
//

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { locationDao.LocationCount() <= 0 }

    override suspend fun saveLocation(location: List<LocationImp>) {
        withContext(Dispatchers.IO) { locationDao.insertLocations(location.map { it.toRoomLocation() }) }
    }


    override suspend fun getLocationsUse(): List<LocationImp> = withContext(Dispatchers.IO) {
        locationDao.getAllLocation().map { it.toDomainLocation() }
    }

    //
    override suspend fun isEmptyCovid19(): Boolean =
        withContext(Dispatchers.IO) { covidDao.covid19Count() <= 0 }

    override suspend fun saveCovid19(covidOverview: Covid19Imp) {
        withContext(Dispatchers.IO) { covidDao.insertCovid(covidOverview.toRoomCovid19()) }
    }

    override suspend fun getCovid19(): Covid19Imp = withContext(Dispatchers.IO) {
        covidDao.getAllDataCovid().toDomainCovid19()
    }


}
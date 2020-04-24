package com.example.testcovid.data.database

import com.example.testcovid.data.source.LocalDataSource
import com.example.testcovid.data.toDomainLatest
import com.example.testcovid.data.toDomainLocation
import com.example.testcovid.data.toRoomLatest
import com.example.testcovid.data.toRoomLocation
import com.example.testcovid.domain.model.LatestImp
import com.example.testcovid.domain.model.LocationImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: LocationDatabase) : LocalDataSource {


    private val latestDao=db.LatestDao()
    private val locationDao = db.LocationDao()


    override suspend fun isEmptyLast(): Boolean = withContext(Dispatchers.IO){latestDao.LatestCount()<=0}


    override suspend fun saveLatest(latest: LatestImp) {
        withContext(Dispatchers.IO){latestDao.insertLatest(latest.toRoomLatest())}
    }

    override suspend fun getLatest(): LatestImp = withContext(Dispatchers.IO){
        latestDao.getAllLatest().toDomainLatest()
    }


        override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { locationDao.LocationCount() <= 0 }

    override suspend fun saveLocation(location: List<LocationImp>) {
        withContext(Dispatchers.IO) { locationDao.insertLocations(location.map { it.toRoomLocation() }) }
    }


    override suspend fun getLocationsUse(): List<LocationImp> = withContext(Dispatchers.IO) {
        locationDao.getAllLocation().map { it.toDomainLocation() }
    }


}
package com.example.testcovid.data


import com.example.testcovid.domain.model.Covid19Imp
import com.example.testcovid.data.database.Covid19 as DomainCovid19
import com.example.testcovid.data.database.Covid19 as ServerCovid19

import com.example.testcovid.domain.model.LocationImp
import com.example.testcovid.data.database.Location as DomainLocation
import com.example.testcovid.data.database.Location as ServerLocation


import com.example.testcovid.domain.model.LatestImp
import com.example.testcovid.data.database.Latest as DomainLatest
import com.example.testcovid.data.database.Latest as ServerLatest


fun LatestImp.toRoomLatest(): DomainLatest =
    DomainLatest(
        confirmed,
        deaths,
        recovered
    )

fun DomainLatest.toDomainLatest(): LatestImp =
    LatestImp(
        confirmed,
        deaths,
        recovered
    )

fun ServerLatest.toDomainServer(): LatestImp =
    LatestImp(
        confirmed,
        deaths,
        recovered
    )

/***
 *
 */


fun LocationImp.toRoomLocation(): DomainLocation =
    DomainLocation(
        id,
        country,
        countryCode,
        countryPopulation,
        province,
        lastUpdated,
        coordinates,
        latestX
    )

fun DomainLocation.toDomainLocation(): LocationImp =
    LocationImp(
        id,
        country,
        countryCode,
        countryPopulation,
        province,
        lastUpdated,
        coordinate,
        latestX
    )

fun ServerLocation.toDomainServer(): LocationImp =
    LocationImp(
        id,
        country,
        countryCode,
        countryPopulation,
        province,
        lastUpdated,
        coordinate,
        latestX
    )

/***
 *
 */

fun Covid19Imp.toRoomCovid19(): DomainCovid19 =
    DomainCovid19(
        id,
        confirmed,
        recovered,
        deaths
    )

fun DomainCovid19.toDomainCovid19(): Covid19Imp =
    Covid19Imp(
        id,
        confirmed,
        recovered,
        deaths
    )

fun ServerCovid19.toDomainServer(): Covid19Imp =
    Covid19Imp(
        0,
        confirmed,
        recovered,
        deaths
    )

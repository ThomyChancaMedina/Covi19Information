package com.example.testcovid.domain.model

import com.example.testcovid.data.database.Location


data class LocationImp(
    val id: Int,
    val country: String,
    val countryCode: String,
    val countryPopulation: Int,
    val province: String,
    val lastUpdated: String,
    val coordinates: Location.Coordinates,
    val latestX:Location.LatestX

)
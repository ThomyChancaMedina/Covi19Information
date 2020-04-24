package com.example.testcovid.data.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName



data class DataCovid(
    val latest: Latest,
    val locations: List<Location>
)


@Entity(
    tableName = "LatestData"
)
data class Latest(
    @PrimaryKey @ColumnInfo(name = "confirmed")
    @field:SerializedName("confirmed")
    val confirmed: Int,
    @field:SerializedName("deaths")
    val deaths: Int,
    @field:SerializedName("recovered")
    val recovered: Int
)



@Entity(
tableName = "LocationData")
data class Location(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    val country: String,

    @field:SerializedName("country_code")
    val countryCode: String,

    @field:SerializedName("country_population")
    val countryPopulation: Int,

    val province: String,

    @field:SerializedName("last_updated")
    val lastUpdated: String,

    @field:SerializedName("coordinates")
    @field:Embedded(prefix = "coordinates_")
    val coordinate:Coordinates,

    @field:SerializedName("latest")
    @field:Embedded(prefix = "latest_")
    val latestX: LatestX

){
    data class Coordinates(
        @field:SerializedName("latitude")
        val latitude: String,
        @field:SerializedName("longitude")
        val longitude: String?
    )

    data class LatestX(
        @field:SerializedName("confirmed")
        val confirmed: Int,
        @field:SerializedName("deaths")
        val deaths: Int,
        @field:SerializedName("recovered")
        val recovered: Int
    )



    companion object{
        const val UNKOWN_ID = -1
    }

}

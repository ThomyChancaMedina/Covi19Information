package com.example.testcovid.domain.model

import com.example.testcovid.data.database.Covid19

//data class ConfirmedImp(
//    val detail: String,
//    val value: Int
//)
//
//data class RecoveredImp(
//    val detail: String,
//    val value: Int
//)
//
//data class DeathsImp(
//    val detail: String,
//    val value: Int
//)

data class Covid19Imp(
    val id:Int,
    val confirmed: Covid19.Confirmed,
    val recovered: Covid19.Recovered,
    val deaths: Covid19.Deaths
)
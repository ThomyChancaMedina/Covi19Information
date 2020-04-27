package com.example.testcovid.data.repository

import android.util.Log
import com.example.testcovid.data.source.DataSource
import com.example.testcovid.data.source.RemoteDataSource
import com.example.testcovid.domain.model.Covid19Imp

class Covid19Repository(
    private val covid19DataSource: DataSource,
    private val remoteDataSource: RemoteDataSource
) {
    val TAG = Covid19Repository::class.java.simpleName
    suspend fun getCovid19(): Covid19Imp {
        if (covid19DataSource.isEmptyCovid19()) {
            val covid = remoteDataSource.getDataCovid19()
            Log.v(TAG, "thomy:: respo:: " + covid.confirmed.detail)
            covid19DataSource.saveCovid19(covid)
        }
        return covid19DataSource.getCovid19()
    }
}
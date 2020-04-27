package com.example.testcovid.domain.usecase

import com.example.testcovid.data.repository.Covid19Repository
import com.example.testcovid.domain.model.Covid19Imp

class GetCovid19Case (
    private val covid19Repository:Covid19Repository
){
    suspend fun invoke(): Covid19Imp=covid19Repository.getCovid19()
}
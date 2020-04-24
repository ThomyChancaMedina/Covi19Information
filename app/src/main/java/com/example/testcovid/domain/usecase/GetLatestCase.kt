package com.example.testcovid.domain.usecase

import com.example.testcovid.data.repository.LatestRepository
import com.example.testcovid.domain.model.LatestImp

class GetLatestCase (
    private val latestRepository: LatestRepository
){
    suspend fun invoke():LatestImp=latestRepository.getLatest()
}
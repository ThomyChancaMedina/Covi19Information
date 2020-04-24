package com.example.testcovid.domain.usecase

import com.example.testcovid.data.repository.LocationRepository
import com.example.testcovid.domain.model.LocationImp


class GetLocationsUseCase constructor(
    private val locationsRepository: LocationRepository
)  {


    suspend fun invoke(): List<LocationImp> = locationsRepository.getLocations()
}
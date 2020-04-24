package com.example.testcovid.di.module

import android.app.Application
import com.example.testcovid.data.database.LocationDatabase
import com.example.testcovid.data.database.RoomDataSource
import com.example.testcovid.data.repository.LatestRepository
import com.example.testcovid.data.repository.LocationRepository
import com.example.testcovid.data.service.TheLocationDb
import com.example.testcovid.data.service.TheLocationDbDataSource
import com.example.testcovid.data.source.LocalDataSource
import com.example.testcovid.data.source.RemoteDataSource
import com.example.testcovid.domain.usecase.GetLatestCase

import com.example.testcovid.domain.usecase.GetLocationsUseCase
import com.example.testcovid.ui.main.MainActivity
import com.example.testcovid.ui.main.MainViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(NetworkModule, dataMoule, PostModule))
    }
}


val NetworkModule = module {

    single { LocationDatabase.build(get()) }
    factory<LocalDataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { TheLocationDbDataSource(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
    single(named("baseUrl")) { "https://coronavirus-tracker-api.herokuapp.com/v2/" }
    single { TheLocationDb(get(named("baseUrl"))) }
}

val dataMoule = module {
    // single instance of PostsRepository
    factory { LocationRepository(get(), get()) }
    factory { LatestRepository(get(),get()) }
}

val PostModule = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get(), get()) }
        scoped { GetLocationsUseCase(get()) }
        scoped { GetLatestCase(get()) }
    }
}









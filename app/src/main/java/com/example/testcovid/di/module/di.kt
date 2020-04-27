package com.example.testcovid.di.module

import android.app.Application
import com.example.testcovid.data.database.CovidDatabase
import com.example.testcovid.data.database.RoomDataSource
import com.example.testcovid.data.repository.Covid19Repository
import com.example.testcovid.data.repository.LatestRepository
import com.example.testcovid.data.repository.LocationRepository
import com.example.testcovid.data.service.TheLocationDb
import com.example.testcovid.data.service.DbDataSource
import com.example.testcovid.data.source.DataSource
import com.example.testcovid.data.source.RemoteDataSource
import com.example.testcovid.domain.usecase.GetCovid19Case
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

    single { CovidDatabase.build(get()) }
    factory<DataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { DbDataSource(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
    single(named("baseUrl")) { "https://covid19.mathdro.id/" }
    single { TheLocationDb(get(named("baseUrl"))) }
}

val dataMoule = module {
    // single instance of Covid19Repository
    factory { LocationRepository(get(), get()) }
    factory { LatestRepository(get(),get()) }
    factory { Covid19Repository(get(),get()) }
}

val PostModule = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get(), get()) }
        scoped { GetLocationsUseCase(get()) }
        scoped { GetLatestCase(get()) }
        scoped { GetCovid19Case(get()) }
    }
}









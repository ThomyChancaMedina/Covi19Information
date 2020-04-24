package com.example.testcovid

import android.app.Application
import androidx.multidex.MultiDex
import com.example.testcovid.di.module.NetworkModule
import com.example.testcovid.di.module.PostModule
import com.example.testcovid.di.module.dataMoule
import com.example.testcovid.di.module.initDI
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MainApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        initDI()

    }
}
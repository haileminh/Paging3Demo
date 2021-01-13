package com.example.paging3demo

import android.app.Application
import com.example.paging3demo.data.local.AppDatabase
import com.example.paging3demo.data.local.LocalInjector
import com.example.paging3demo.di.networkModule
import com.example.paging3demo.di.repositoryModule
import com.example.paging3demo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        LocalInjector.appDatabase = AppDatabase.getInstance(this@App)
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}
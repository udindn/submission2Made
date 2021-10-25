package com.arrayyan.dicoding.app

import android.app.Application
import com.arrayyan.core.di.databaseModule
import com.arrayyan.core.di.networkModule
import com.arrayyan.core.di.repositoryModule
import com.arrayyan.dicoding.di.useCaseModule
import com.arrayyan.dicoding.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@Application)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
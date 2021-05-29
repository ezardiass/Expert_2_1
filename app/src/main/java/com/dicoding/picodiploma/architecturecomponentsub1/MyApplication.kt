package com.dicoding.picodiploma.architecturecomponentsub1

import android.app.Application
import com.dicoding.picodiploma.architecturecomponentsub1.di.AppModule.useCaseModule
import com.dicoding.picodiploma.architecturecomponentsub1.di.AppModule.viewModelModule
import com.dicoding.picodiploma.core.di.CoreModule.databaseModule
import com.dicoding.picodiploma.core.di.CoreModule.networkModule
import com.dicoding.picodiploma.core.di.CoreModule.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            val androidContext = androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}


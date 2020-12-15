package com.saulwiggin.breakingbadactormodule

import android.app.Application
import com.saulwiggin.breakingbadactormodule.internal.di.apiModule
import com.saulwiggin.breakingbadactormodule.internal.di.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyBreakingBadApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyBreakingBadApplication)
            androidLogger(Level.DEBUG)
            modules(listOf(viewmodelModule, apiModule, networkModule, repositoryModule, databaseModule))
        }
    }
}
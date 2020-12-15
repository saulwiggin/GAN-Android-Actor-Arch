package com.saulwiggin.breakingbadactormodule.internal.di.di

import android.app.Application
import androidx.room.Room
import com.saulwiggin.breakingbadactormodule.data.db.ActorDao
import com.saulwiggin.breakingbadactormodule.data.db.ActorDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(application: Application): ActorDatabase {
        return Room.databaseBuilder(application,
            ActorDatabase::class.java,
            "actor-db"
        ).build()
    }

    fun provideDao(database: ActorDatabase): ActorDao {
        return database.actorDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get())}
}
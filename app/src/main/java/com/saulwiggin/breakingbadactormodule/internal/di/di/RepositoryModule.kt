package com.saulwiggin.breakingbadactormodule.internal.di.di

import com.saulwiggin.breakingbadactormodule.data.repository.ActorRepository
import com.saulwiggin.breakingbadactormodule.data.repository.ActorRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single {
        ActorRepositoryImpl(get(), get()) as ActorRepository
    }
}
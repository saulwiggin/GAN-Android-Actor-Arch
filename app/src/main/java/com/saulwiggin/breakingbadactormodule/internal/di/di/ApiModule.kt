package com.saulwiggin.breakingbadactormodule.internal.di.di

import com.saulwiggin.breakingbadactormodule.data.network.ActorApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single {
        get<Retrofit>().create(ActorApiService::class.java)
    }
}
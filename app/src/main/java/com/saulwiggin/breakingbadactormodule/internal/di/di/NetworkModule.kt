package com.saulwiggin.breakingbadactormodule.internal.di.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.saulwiggin.breakingbadactormodule.data.datasource.ActorDataSource
import com.saulwiggin.breakingbadactormodule.data.datasource.ActorDataSourceImpl
import com.saulwiggin.breakingbadactormodule.internal.di.Constants
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        val cache = Cache(
            androidApplication().cacheDir,
            10 * 1024 * 1024
        )

        OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    single {
        ActorDataSourceImpl(get()) as ActorDataSource
    }

}
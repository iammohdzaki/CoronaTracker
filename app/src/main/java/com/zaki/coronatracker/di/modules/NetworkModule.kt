package com.zaki.coronatracker.di.modules

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Developer : Mohammad Zaki
 * Created On : 08-03-2020
 */

val networkModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single {
        OkHttpClient.Builder().apply {
            addInterceptor(get<HttpLoggingInterceptor>())
            addInterceptor { chain ->

                //Log.d("AppModule", token)

                val request: Request =
                    chain.request()
                        .newBuilder()
                        // .addHeader("x-access-token", get<Storage>().getString("token"))
                        .build()

                chain.proceed(request)
            }
        }.build()
    }
    single {
        Retrofit.Builder()
            //.baseUrl(BuildConfig.BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
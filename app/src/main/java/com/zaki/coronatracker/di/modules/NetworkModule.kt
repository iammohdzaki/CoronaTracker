package com.zaki.coronatracker.di.modules

import com.zaki.coronatracker.apis.Apis
import com.zaki.coronatracker.utils.API_KEY
import com.zaki.coronatracker.utils.BASE_URL
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
                val request: Request =
                    chain.request()
                        .newBuilder()
                        .build()

                chain.proceed(request)
            }
        }.build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create(Apis::class.java)
    }
}
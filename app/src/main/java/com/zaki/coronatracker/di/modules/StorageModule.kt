package com.zaki.coronatracker.di.modules

import com.zaki.coronatracker.storage.SharedPreferencesStorage
import com.zaki.coronatracker.storage.Storage
import org.koin.dsl.module

/**
 * Developer : Mohammad Zaki
 * Created On : 01-04-2020
 */
val storageModules = module {
    single { SharedPreferencesStorage(get()) as Storage }
}
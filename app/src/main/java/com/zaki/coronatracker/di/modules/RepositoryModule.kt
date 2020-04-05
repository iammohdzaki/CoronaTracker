package com.zaki.coronatracker.di.modules

import com.zaki.coronatracker.apis.ApiRepository
import org.koin.dsl.module

/**
 * Developer : Mohammad Zaki
 * Created On : 05-04-2020
 */

val repositoryModules= module {
    single { ApiRepository(get()) }
}
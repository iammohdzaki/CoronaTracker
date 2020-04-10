package com.zaki.coronatracker.di.modules

import com.zaki.coronatracker.features.countries.CountriesViewModel
import com.zaki.coronatracker.features.home.HomeViewModel
import org.koin.dsl.module

/**
 * Developer : Mohammad Zaki
 * Created On : 01-04-2020
 */

val viewModelModules = module {
    single { HomeViewModel(get()) }
    single { CountriesViewModel(get()) }
}
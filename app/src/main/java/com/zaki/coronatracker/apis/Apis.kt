package com.zaki.coronatracker.apis

import com.zaki.coronatracker.model.CountryStats
import com.zaki.coronatracker.model.GlobalStats
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Developer : Mohammad Zaki
 * Created On : 30-03-2020
 */

interface Apis {

    @GET("/v2/all")
    suspend fun getGlobalStats() : GlobalStats

    @GET("/v2/countries?sort=cases")
    suspend fun getAllCountries():ArrayList<CountryStats>

    @GET("/v2/countries")
    suspend fun getCountry(@Query("countryName") countryName:String):CountryStats
}
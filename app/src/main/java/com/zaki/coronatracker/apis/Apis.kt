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

    @GET("/all")
    suspend fun getGlobalStats() : GlobalStats

    @GET("/countries?sort=cases")
    suspend fun getAllCountries():ArrayList<CountryStats>

    @GET("/countries")
    suspend fun getCountry(@Query("countryName") countryName:String):CountryStats
}
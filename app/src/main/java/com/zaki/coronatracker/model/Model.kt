package com.zaki.coronatracker.model

import com.google.gson.annotations.SerializedName

/**
 * Developer : Mohammad Zaki
 * Created On : 30-03-2020
 */

data class GlobalStats(
    @SerializedName("cases") var totalCases: Int = 0,
    @SerializedName("todayCases") var todayCases: Int = 0,
    @SerializedName("deaths") var totalDeaths: Int = 0,
    @SerializedName("todayDeaths") var todayDeaths: Int = 0,
    @SerializedName("recovered") var totalRecovered: Int = 0,
    @SerializedName("active") var activeCases: Int = 0,
    @SerializedName("critical") var criticalCases: Int = 0,
    @SerializedName("casesPerOneMillion") var casesPerOneMillion: Int = 0,
    @SerializedName("deathsPerOneMillion") var deathsPerOneMillion: Double = 0.0,
    @SerializedName("affectedCountries") var affectedCountries: Int = 0,
    @SerializedName("updated") var lastUpdated: Long = 0L
)

data class CountryStats(
    @SerializedName("country") var countryName: String = "",
    @SerializedName("countryInfo") var countryInfo: CountryInfo,
    @SerializedName("cases") var totalCases: Int = 0,
    @SerializedName("todayCases") var todayCases: Int = 0,
    @SerializedName("deaths") var totalDeaths: Int = 0,
    @SerializedName("recovered") var totalRecovered: Int = 0,
    @SerializedName("active") var activeCases: Int = 0,
    @SerializedName("critical") var criticalCases: Int = 0,
    @SerializedName("casesPerOneMillion") var casesPerOneMillion: Int = 0,
    @SerializedName("deathsPerOneMillion") var deathsPerOneMillion: Double = 0.0,
    @SerializedName("updated") var lastUpdated: Long = 0L
    )

data class CountryInfo(
    @SerializedName("_id") var _id: Int = 0,
    @SerializedName("iso2") var iso2: String = "",
    @SerializedName("iso3") var iso3: String = "",
    @SerializedName("lat") var latitude: Double = 0.0,
    @SerializedName("long") var longitude: Double = 0.0,
    @SerializedName("flag") var flag: String = ""
)
package com.zaki.coronatracker.features.countries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaki.coronatracker.apis.ApiRepository
import com.zaki.coronatracker.model.CommonResponse
import com.zaki.coronatracker.model.CountryStats
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Developer : Mohammad Zaki
 * Created On : 07-04-2020
 */

class CountriesViewModel(private val apiRepository: ApiRepository) : ViewModel(){

    val countriesList: MutableLiveData<CommonResponse<ArrayList<CountryStats>>> = MutableLiveData()

    init {
        getCountriesData()
    }

    private fun getCountriesData(){
        viewModelScope.launch(Dispatchers.IO){
            val countriesData = apiRepository.getCountriesList()
            if(countriesData != null){
                countriesList.postValue(CommonResponse.Success(countriesData))
            }else{
                countriesList.postValue(CommonResponse.Failure(Throwable("Error while Retrieving Data")))
            }
        }

    }
}
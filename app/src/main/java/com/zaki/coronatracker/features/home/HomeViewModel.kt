package com.zaki.coronatracker.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaki.coronatracker.apis.ApiRepository
import com.zaki.coronatracker.extensions.APIResult
import com.zaki.coronatracker.model.CommonResponse
import com.zaki.coronatracker.model.GlobalStats
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Developer : Mohammad Zaki
 * Created On : 01-04-2020
 */

class HomeViewModel(private val apiRepository: ApiRepository): ViewModel(){

    val globalStatsData:MutableLiveData<CommonResponse<GlobalStats>> = MutableLiveData()

    init {
        getGlobalStats()
    }

    private fun getGlobalStats(){
        viewModelScope.launch(Dispatchers.IO){
            val globalStats= apiRepository.getGlobalStats()
            if(globalStats != null){
                globalStatsData.postValue(CommonResponse.Success(globalStats))
            }else{
                globalStatsData.postValue(CommonResponse.Failure(Throwable("Error while Retrieving Data")))
            }
        }
    }
}
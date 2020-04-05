package com.zaki.coronatracker.apis

import android.util.Log
import com.zaki.coronatracker.model.GlobalStats
import java.lang.Exception

/**
 * Developer : Mohammad Zaki
 * Created On : 05-04-2020
 */

class ApiRepository(private var service:Apis){

    suspend fun getGlobalStats(): GlobalStats?{
        return try {
            service.getGlobalStats()
        }catch (e:Exception){
            Log.e(ApiRepository::class.java.name, e.toString())
            null
        }
    }
}
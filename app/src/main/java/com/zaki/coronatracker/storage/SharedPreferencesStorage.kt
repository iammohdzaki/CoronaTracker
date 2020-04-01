package com.zaki.coronatracker.storage

import android.content.Context

class SharedPreferencesStorage(context: Context) : Storage {

    private val sharedPreferences = context.getSharedPreferences("chef_app", Context.MODE_PRIVATE)

    override fun setString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }

}
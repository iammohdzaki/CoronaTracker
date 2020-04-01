package com.zaki.coronatracker.features.base

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

/**
 * Developer : Mohammad Zaki
 * Created On : 30-03-2020
 */

open class BaseActivity : AppCompatActivity(){

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    fun setOnClickListeners(onClickListener: View.OnClickListener,vararg view:View){
        for(v in view){
            v.setOnClickListener(onClickListener)
        }
    }
}
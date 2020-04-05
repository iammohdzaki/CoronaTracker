package com.zaki.coronatracker

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.zaki.coronatracker.di.modules.networkModule
import com.zaki.coronatracker.di.modules.repositoryModules
import com.zaki.coronatracker.di.modules.storageModules
import com.zaki.coronatracker.di.modules.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import java.lang.ref.WeakReference

/**
 * Developer : Mohammad Zaki
 * Created On : 30-03-2020
 */

class CoronaApp :Application(),LifecycleObserver{

    override fun onCreate() {
        super.onCreate()

        initializeKoin()

        myApplicationContext= WeakReference(this)

        //Font Library initialization
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("font/Montserrat-Bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@CoronaApp)
            modules(
                listOf(
                    networkModule,
                    repositoryModules,
                    storageModules,
                    viewModelModules
                )
            )
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        foreground = false
        Timber.d("FALSE")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForgrounded() {
        foreground = true
        Timber.d("TRUE")
    }

    companion object {
        private var foreground = false
        fun isForeground(): Boolean {
            return foreground
        }

        @JvmStatic
        private lateinit var myApplicationContext: WeakReference<Context>

        @JvmStatic
        fun getMyApplicationContext(): WeakReference<Context> {
            return myApplicationContext
        }
    }
}
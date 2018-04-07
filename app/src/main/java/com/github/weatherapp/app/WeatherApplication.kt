package com.github.weatherapp.app

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.github.weatherapp.BuildConfig
import timber.log.Timber

/**
 * Created by Manokar on 4/7/18.
 */
class WeatherApplication : Application() {
    companion object {
        lateinit var instance: WeatherApplication
        fun checkIfHasNetwork(): Boolean {
            val cm = instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        initTimber()
    }

    fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
package com.github.weatherapp.app

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.github.weatherapp.BuildConfig
import com.github.weatherapp.di.ApiModule
import com.github.weatherapp.di.ApplicationComponent
import com.github.weatherapp.di.DaggerApplicationComponent
import timber.log.Timber

/**
 * Created by Manokar on 4/7/18.
 */
open class WeatherApplication : Application() {

    companion object {

        lateinit var applicationComponent : ApplicationComponent

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
        initDi()
    }

    private fun initDi() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .apiModule(ApiModule())
                .build()
    }

    fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
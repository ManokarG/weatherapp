package com.github.weatherapp.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Manokar on 4/8/18.
 */
@Module
class ApplicationModule(private val application : Application){

    @Provides @Singleton
    fun providesContext() : Context {
        return application
    }
}
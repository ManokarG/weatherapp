package com.github.weatherapp.di.modules

import android.content.Context
import com.github.weatherapp.R
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Manokar on 4/8/18.
 */
@Module
class ApiKeyModule {

    @Provides
    @Singleton
    @Named("key")
    fun providesWeatherApiKey(context: Context): String = context.getString(R.string.api_key)

}
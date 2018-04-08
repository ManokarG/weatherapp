package com.github.weatherapp.di.components

import android.content.Context
import com.github.weatherapp.data.ApiService
import com.github.weatherapp.di.modules.*
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Manokar on 4/8/18.
 */
@Singleton
@Component(modules = arrayOf(ApiModule::class, ApiKeyModule::class, ApplicationModule::class, CacheModule::class, InterceptorModule::class))
interface ApplicationComponent {
    fun provideApiService() : ApiService

    @Named("key") fun provideKey() : String

}
package com.github.weatherapp.di.modules

import com.github.weatherapp.BuildConfig
import com.github.weatherapp.Constants
import com.github.weatherapp.app.WeatherApplication
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named

/**
 * Created by Manokar on 4/8/18.
 */
@Module
class InterceptorModule {

    companion object {
        private val USER_AGENT = "User-Agent"
        private val ADEPT_ANDROID_APP = "Adept-Android-App"
        private val VERSION = "version"
        private val CACHE_CONTROL = "Cache-Control"
    }

    @Provides
    @Named(Constants.CACHE_INTERCEPTOR)
    fun provideCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())

            // re-write response header to force use of cache
            val cacheControl = CacheControl.Builder()
                    .maxAge(2, TimeUnit.MINUTES)
                    .build()

            response.newBuilder()
                    .header(CACHE_CONTROL, cacheControl.toString())
                    .build()
        }
    }

    @Provides
    @Named(Constants.URL_HEADER_INTERCEPTOR)
    fun provideUrlAndHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()
                    .addHeader(USER_AGENT, ADEPT_ANDROID_APP)

            val url = request.url()
                    .newBuilder()
                    .addQueryParameter(VERSION, BuildConfig.VERSION_NAME)
                    .build()

            builder.url(url)

            chain.proceed(builder.build())
        }
    }

    @Provides
    @Named(Constants.OFFLINE_CACHE_INTERCEPTOR)
    fun provideOfflineCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (!WeatherApplication.checkIfHasNetwork()) { // If no network
                val cacheControl = CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS) // Stale for 7 days, with the expired cache
                        .build()

                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build()
            }

            chain.proceed(request);
        }
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(
                HttpLoggingInterceptor.Logger { message -> Timber.d(message) })
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.HEADERS else HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
    }

}
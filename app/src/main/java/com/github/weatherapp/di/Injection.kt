package com.github.weatherapp.di

import com.github.weatherapp.BuildConfig
import com.github.weatherapp.Constants
import com.github.weatherapp.app.WeatherApplication
import com.github.weatherapp.data.ApiService
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by Manokar on 4/7/18.
 */
class Injection {

    companion object {
        private val USER_AGENT = "User-Agent"
        private val ADEPT_ANDROID_APP = "Adept-Android-App"
        private val CACHE_CONTROL = "Cache-Control"

        fun getOkHttpClient(): OkHttpClient {
            val okHttpClient: OkHttpClient =
                    OkHttpClient.Builder()
                            .addInterceptor(provideHttpLoggingInterceptor())
                            .addInterceptor(provideUrlAndHeaderInterceptor())
                            .addInterceptor(provideOfflineCacheInterceptor())
                            .addInterceptor(provideKeyInterceptor())
                            .cache(provideCache())
                            .addNetworkInterceptor(provideCacheInterceptor())
                            .build()

            return okHttpClient
        }

        private fun provideKeyInterceptor(): Interceptor {
            return Interceptor { chain ->
                val request = chain.request()
                val builder = request.newBuilder()

                val url = request.url()
                        .newBuilder()
                        .addQueryParameter("key", Constants.key)
                        .build()

                builder.url(url)

                chain.proceed(builder.build())
            }
        }


        private fun provideCache(): Cache? {
            var cache: Cache? = null
            try {
                cache = Cache(File(WeatherApplication.instance.cacheDir, "http-cache"),
                        1024 * 1024 * 10)
            } catch (e: Exception) {
                Timber.e(e, "couldn't  create cache")
            }

            return cache
        }


        private fun provideCacheInterceptor(): Interceptor {
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

        private fun provideUrlAndHeaderInterceptor(): Interceptor {
            return Interceptor { chain ->
                val request = chain.request()
                val builder = request.newBuilder()
                        .addHeader(USER_AGENT, ADEPT_ANDROID_APP)

                val url = request.url()
                        .newBuilder()
                        .build()

                builder.url(url)

                chain.proceed(builder.build())
            }
        }

        private fun provideOfflineCacheInterceptor(): Interceptor {
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

        private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
            val httpLoggingInterceptor = HttpLoggingInterceptor(
                    HttpLoggingInterceptor.Logger { message -> Timber.d(message) })
            httpLoggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.HEADERS else HttpLoggingInterceptor.Level.NONE
            return httpLoggingInterceptor
        }

        fun provideRetrofit(): Retrofit {
            val httpClient = getOkHttpClient()
            val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build()

            return retrofit
        }

        fun provideApiService(): ApiService {
            return provideRetrofit()
                    .create(ApiService::class.java)
        }
    }
}
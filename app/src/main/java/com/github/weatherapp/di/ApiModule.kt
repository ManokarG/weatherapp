package com.github.weatherapp.di

import com.github.weatherapp.Constants
import com.github.weatherapp.data.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Manokar on 4/8/18.
 */
@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient{
        return OkHttpClient.Builder()
                /*.addInterceptor(httpLoggingInterceptor)
                .addInterceptor(urlAndHeaderInterceptor)
                .addInterceptor(offlineCacheInterceptor)
                .cache(cache)
                .addNetworkInterceptor(cacheInterceptor)*/
                .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }

}
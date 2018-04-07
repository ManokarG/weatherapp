package com.github.weatherapp.data

import com.github.weatherapp.Constants
import com.github.weatherapp.data.models.WeatherForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Manokar on 4/7/18.
 */
interface ApiService {
    @GET("v1/forecast.json")
    fun getWeatherData(
            @Query("q") city: String,
            @Query("days") totalCount: Int): Call<WeatherForecast>
}
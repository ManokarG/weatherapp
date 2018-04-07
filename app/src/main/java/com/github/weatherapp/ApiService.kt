package com.github.weatherapp

import com.github.weatherapp.models.WeatherForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Manokar on 4/7/18.
 */
interface ApiService {
    @GET("forecast.json?key=a442bb52ef5146a484d145959180704")
    fun getWeatherData(
            @Query("q") city: String,
            @Query("days") totalCount: Int): Call<WeatherForecast>
}
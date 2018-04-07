package com.github.weatherapp.data.models

/**
 * Created by Manokar on 4/7/18.
 */
data class WeatherForecast(
        val current: Current? = null,
        val location: Location? = null,
        val forecast: Forecast? = null
)
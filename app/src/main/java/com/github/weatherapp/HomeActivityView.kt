package com.github.weatherapp

import com.github.weatherapp.models.WeatherForecast

/**
 * Created by Manokar on 4/7/18.
 */
interface HomeActivityView {
    fun showErrorView()
    fun showWeatherForecast(body: WeatherForecast?)
}
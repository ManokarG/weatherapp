package com.github.weatherapp.ui.view

import com.github.weatherapp.data.models.WeatherForecast

/**
 * Created by Manokar on 4/7/18.
 */
interface HomeActivityView {
    fun showErrorView()
    fun showWeatherForecast(body: WeatherForecast?)
}
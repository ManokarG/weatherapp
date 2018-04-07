package com.github.weatherapp.data.models

/**
 * Created by Manokar on 4/7/18.
 */
data class ForecastdayItem(
        val date: String? = null,
        val astro: Astro? = null,
        val date_epoch: Int? = null,
        val hour: List<HourItem?>? = null,
        val day: Day? = null
)
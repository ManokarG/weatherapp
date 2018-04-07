package com.github.weatherapp.data.models

/**
 * Created by Manokar on 4/7/18.
 */
data class Location(
        val localtime: String? = null,
        val country: String? = null,
        val localtime_epoch: Int? = null,
        val name: String? = null,
        val lon: Double? = null,
        val region: String? = null,
        val lat: Double? = null,
        val tz_id: String? = null
)
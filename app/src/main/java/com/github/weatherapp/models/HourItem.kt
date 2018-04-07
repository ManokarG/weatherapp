package com.github.weatherapp.models

/**
 * Created by Manokar on 4/7/18.
 */
data class HourItem(
        val feelslike_c: Double? = null,
        val feelslike_f: Double? = null,
        val wind_degree: Int? = null,
        val windchill_f: Double? = null,
        val windchill_c: Double? = null,
        val temp_c: Double? = null,
        val temp_f: Double? = null,
        val cloud: Int? = null,
        val wind_kph: Double? = null,
        val wind_mph: Double? = null,
        val humidity: Int? = null,
        val dewpoint_f: Double? = null,
        val will_it_rain: Int? = null,
        val heatindex_f: Double? = null,
        val dewpoint_c: Double? = null,
        val is_day: Int? = null,
        val precip_in: Double? = null,
        val heatindex_c: Double? = null,
        val wind_dir: String? = null,
        val pressure_in: Double? = null,
        val chance_of_rain: String? = null,
        val precip_mm: Double? = null,
        val condition: Condition? = null,
        val will_it_snow: Int? = null,
        val vis_km: Double? = null,
        val time_epoch: Int? = null,
        val time: String? = null,
        val chance_of_snow: String? = null,
        val pressure_mb: Double? = null,
        val vis_miles: Double? = null
)
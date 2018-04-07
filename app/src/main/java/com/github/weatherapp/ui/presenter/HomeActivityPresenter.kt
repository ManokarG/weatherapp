package com.github.weatherapp.ui.presenter

import com.github.weatherapp.ui.view.HomeActivityView
import com.github.weatherapp.data.ApiService
import com.github.weatherapp.data.models.WeatherForecast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * Created by Manokar on 4/7/18.
 */
class HomeActivityPresenter(private val homeActivityView : HomeActivityView,
                            private val apiService : ApiService) {
    fun fetchWeatherData() {
        apiService.getWeatherData("Chennai", 4)
                .enqueue(object : Callback<WeatherForecast> {
                    override fun onResponse(call: Call<WeatherForecast>?, response: Response<WeatherForecast>?) {
                        response?.let {
                            if(response.isSuccessful){
                                homeActivityView.showWeatherForecast(response.body())
                            }
                        }
                    }

                    override fun onFailure(call: Call<WeatherForecast>?, t: Throwable?) {
                        homeActivityView.showErrorView()
                        Timber.e(t)
                    }

                })
    }
}

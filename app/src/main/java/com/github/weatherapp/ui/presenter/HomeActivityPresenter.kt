package com.github.weatherapp.ui.presenter

import com.github.weatherapp.ui.view.HomeActivityView
import com.github.weatherapp.data.ApiService
import com.github.weatherapp.data.models.WeatherForecast
import com.github.weatherapp.util.RxUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Manokar on 4/7/18.
 */
class HomeActivityPresenter @Inject constructor(
                             private val homeActivityView : HomeActivityView,
                            private val apiService : ApiService) {

    fun fetchWeatherData() {
        apiService.getWeatherData("Chennai", 4)
                .compose {
                    RxUtil.applySchedulersToSingleObservable(it)
                }
                .subscribe({
                    homeActivityView.showWeatherForecast(it)
                }, {
                    homeActivityView.showErrorView()
                    Timber.e(it)
                });
    }

}

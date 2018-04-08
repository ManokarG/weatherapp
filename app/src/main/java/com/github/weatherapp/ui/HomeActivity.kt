package com.github.weatherapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.weatherapp.ui.view.HomeActivityView
import com.github.weatherapp.di.Injection
import com.github.weatherapp.R
import com.github.weatherapp.app.WeatherApplication
import com.github.weatherapp.data.models.WeatherForecast
import com.github.weatherapp.ui.presenter.HomeActivityPresenter
import com.github.weatherapp.ui.presenter.HomeActivityPresenterModule
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeActivityView {

    @Inject
    lateinit var presenter : HomeActivityPresenter

    var activityComponent : HomeActivityComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDi()

        presenter.fetchWeatherData()

    }

    private fun initDi() {
        activityComponent = DaggerHomeActivityComponent.builder()
                .homeActivityPresenterModule(HomeActivityPresenterModule(this))
                .applicationComponent(WeatherApplication.applicationComponent)
                .build()

        activityComponent?.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        activityComponent = null
    }

    override fun showErrorView() {
        tvWeather.text = "Cannot receive weather data"
    }

    override fun showWeatherForecast(body: WeatherForecast?) {
        tvWeather.text = body?.current?.temp_c?.toString()
    }

}

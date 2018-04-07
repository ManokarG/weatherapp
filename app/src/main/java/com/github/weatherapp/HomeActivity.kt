package com.github.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.weatherapp.models.WeatherForecast
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity(), HomeActivityView{

    lateinit var presenter : HomeActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = HomeActivityPresenter(this, Injection.provideApiService())

        presenter.fetchWeatherData()
    }

    override fun showErrorView() {
        tvWeather.text = "Cannot receive weather data"
    }

    override fun showWeatherForecast(body: WeatherForecast?) {
        tvWeather.text = body?.current?.temp_c?.toString()
    }

}

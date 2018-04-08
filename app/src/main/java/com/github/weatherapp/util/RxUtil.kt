package com.github.weatherapp.util

import com.github.weatherapp.data.models.WeatherForecast
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Manokar on 4/8/18.
 */
class RxUtil{
    companion object {
        fun <T> applySchedulersToSingleObservable(upstream : Single<T>) : Single<T>{
            return upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}
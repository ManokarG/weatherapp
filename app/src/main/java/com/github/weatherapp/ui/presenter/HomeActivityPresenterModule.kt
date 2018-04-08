package com.github.weatherapp.ui.presenter

import com.github.weatherapp.ui.PerActivity
import com.github.weatherapp.ui.view.HomeActivityView
import dagger.Module
import dagger.Provides

/**
 * Created by Manokar on 4/8/18.
 */
@Module
class HomeActivityPresenterModule(private val homeActivityView: HomeActivityView) {

    @Provides @PerActivity
    fun providesHomeActivityView() : HomeActivityView = homeActivityView

}
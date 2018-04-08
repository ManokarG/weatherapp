package com.github.weatherapp.ui

import com.github.weatherapp.di.ApplicationComponent
import com.github.weatherapp.ui.presenter.HomeActivityPresenterModule
import dagger.Component

/**
 * Created by Manokar on 4/8/18.
 */
@PerActivity
@Component( dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(HomeActivityPresenterModule::class))
interface HomeActivityComponent {
    fun inject(homeActivity: HomeActivity)
}
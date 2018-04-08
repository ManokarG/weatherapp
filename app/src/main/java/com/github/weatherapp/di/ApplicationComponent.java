package com.github.weatherapp.di;

import com.github.weatherapp.data.ApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Manokar on 4/8/18.
 */

@Singleton @Component(modules = ApiModule.class)
public interface ApplicationComponent {
    ApiService provideApiService();
}
